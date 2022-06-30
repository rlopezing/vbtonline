package ve.com.vbtonline.vista.action.creditCards;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.venezolano.util.text.NullFormatter;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.creditCards.ICreditCardsServicio;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.validacionUtil;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: Rafael Godoy
 * Date: 13/07/2012
 * Time: 05:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreditCardsAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(CreditCardsAction.class);
    private FabricaTo fabrica;
    private CreditCardsOd creditCard;
    private ICreditCardsServicio creditCardsServicio;
    private String mensaje;
    private DataJson data=new DataJson();
    private String jsonCreditCards;
    private String estatusTarjeta;
    private String fechaBloqueo;
    private String motivoBloqueo;
    private String permiteReactivar;
    private Map session;
    private List<ContentSelectOd> tarjetas;
    private List<ContentSelectOd> tarjetasITT;
    private List<ContentSelectOd> tarjetasCL;
    private List<ContentSelectOd> tarjetasCLE;
    private List<ContentSelectOd> motivosCLE;
    private List<ContentSelectOd> fecha;
    private List<String> detalles;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasTest;
    private List<ContentsTableColumnsOd> tablaActivas_culumnas;
    private List<ContentsTableColumnsOd> tablaHistorico_culumnas;
    private List<ContentTableInfoOd> contenidoTabla_infoTest;
    private List<ContentTableInfoOd> contenidoTablaActivas_info;
    private List<ContentTableInfoOd> contenidoTablaHistorico_info;
    private List<ContentsTableColumnsOd> contenidoTablaDetalle_culumnas;
    private List<ContentTableInfoOd> contenidoTablaDetalle_info;
    private String idioma;
    private String codigo;
    private List<List<String>> feriados;
    private List<String> detalle_pagoTDC;
    private List<ContentSelectOd> tipoPagoTDC;
    private List<ContentSelectOd> cuentasTOB;
    private String respuesta;
    private String generica;


    public CreditCardsAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }



    public String execute() throws Exception {
        final String origen = "creditCardsAction.execute";
        long time;
        CreditCardsOd creditCard;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }


    public String cargar() throws Exception {
        final String origen = "creditCardsAccion.cargar";
        long time;
        CreditCardsOd creditCard;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            VBTUsersOd usuario = (VBTUsersOd) session.get("user");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            creditCard=getCreditCardsServicio().cargar("", data.getCreditCardss().get(0), usuario);
            setMensaje(creditCard.getId().toString());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarTarjetasEstadoCuentaTDC() throws Exception {
        final String origen = "CreditCardsAction.cargarTarjetasEstadoCuentaTDC";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        SelectOd tarjeta = new SelectOd();
        SelectOd meses = new SelectOd();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");
            tarjeta=getCreditCardsServicio().cargarTarjetasEstadoCuentaTDC(carteras, idioma, usuario);
            tarjetas=tarjeta.getContenido();

            meses = getCreditCardsServicio().cargarMesesEstadoCuentaTDC(null, usuario);
            fecha = meses.getContenido();
            session.put("tarjetas",tarjetas);

            if(tarjetas == null || tarjetas.size()==0)
                codigo = "1";
            else
                codigo = "0";
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarTarjetasCL() throws Exception {
        final String origen = "CreditCardsAction.cargarTarjetasCL";
        long time;
        List<String> parametros;
        String carteras= new String();
        SelectOd tarjeta = new SelectOd();
        VBTUsersOd usersOd = null;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");
            usersOd = (VBTUsersOd) session.get("user");
            tarjeta=getCreditCardsServicio().cargarTarjetasTDCCL(carteras, idioma, usersOd);
            tarjetasCL=tarjeta.getContenido();
            session.put("tarjetasCL",tarjetasCL);

            if(tarjetasCL == null || tarjetasCL.size()==0)
                codigo = "1";
            else
                codigo = "0";

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e,e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarPagosTdc() throws Exception {
        final String origen = "CreditCardAccion.cargarPagosTdc";
        long time;

        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        String carteras = (String) session.get("carteras");
        idioma = (String) session.get("idioma");
        String status;
        carteras = carteras.replaceAll("\'", ""); //Elimina las comillas simples para que pueda funcionar la consulta

        try {

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            status = data.getParametros().get(0);

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + CreditCardsAction.class + " | " + origen);

            time = System.currentTimeMillis();

            TableOd tableOd = getCreditCardsServicio().consultarPagosTdc(carteras, status, idioma, usuario);

            contenidoTabla_culumnasTest = tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest = tableOd.getContenidoTabla_info();


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + CreditCardsAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            getCreditCardsServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarTdcPagos() throws Exception {
        final String origen = "CreditCardsAction.cargarTdcPagos";
        long time;
        List<String> parametros;
        String carteras= new String();
        SelectOd tarjeta = new SelectOd();
        VBTUsersOd usersOd = null;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");
            usersOd = (VBTUsersOd) session.get("user");
            tarjeta=getCreditCardsServicio().cargarTdcPagos(carteras, idioma, usersOd);
            tarjetasCL=tarjeta.getContenido();
            session.put("tarjetasCL",tarjetasCL);

            if(tarjetasCL == null || tarjetasCL.size()==0)
                codigo = "1";
            else
                codigo = "0";

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e,e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarTarjetasITT() throws Exception {
        final String origen = "CreditCardsAction.cargarTarjetasITT";
        long time;
        List<String> parametros;
        String carteras= new String();
        SelectOd tarjeta = new SelectOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");
            tarjeta=getCreditCardsServicio().cargarTarjetasTDCITT(carteras, idioma, usuario);
            tarjetasITT=tarjeta.getContenido();
            session.put("tarjetasITT",tarjetasITT);

            if(tarjetasITT == null || tarjetasITT.size()==0)
                codigo = "1";
            else
                codigo = "0";



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }





    public String cargarDetalleYMovimientosEstadoCuentaTDC() throws Exception {
        final String origen = "CreditCardsAction.cargarDetalleYMovimientosEstadoCuentaTDC";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        SelectOd tarjeta = new SelectOd();
        SelectOd meses = new SelectOd();
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));
            parametros.add("002");
            parametros.add(data.getParametros().get(1));


            List<ContentSelectOd> tarjetas = (List<ContentSelectOd> ) session.get("tarjetas");
            if(validar.validarTDC(tarjetas,data.getParametros().get(0)).equalsIgnoreCase("SI")){
                detalles=getCreditCardsServicio().consultarDetallesEstadoCuentaTDC(parametros, usuario);
                idioma = (String) session.get("idioma");
                TableOd tabla = getCreditCardsServicio().consultarSaldosEstadoCuentaTDC(parametros, idioma, usuario);
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                codigo = "0";
                // strCmbCodInstrumento
                this.GuardarLog(usuario.getLogin(),"3","1","9",data.getParametros().get(0),usuario.getIP(),"Edo. Cuenta Fecha:" + data.getParametros().get(1));
            }else{
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                    setMensaje("El número de tarjeta de crédito no coincide con los asignados a su cuenta, por favor inserte un número de tarjeta de crédito válido ");
                else
                    setMensaje("The credit card  number does not match those assigned to your account, please insert a valid credit card number ");
                codigo = "1";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecucion de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarMovimientosTDCITT() throws Exception {
        final String origen = "CreditCardsAction.cargarMovimientosTDCITT";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));

            idioma = (String) session.get("idioma");
            List<ContentSelectOd> tarjetas = (List<ContentSelectOd> ) session.get("tarjetasITT");
            if(validar.validarTDCTransito(tarjetas,data.getParametros().get(0)).equalsIgnoreCase("SI")){
                TableOd tabla = getCreditCardsServicio().cargarMovimientosTDCITT(parametros, idioma, usuario);
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                detalles= getCreditCardsServicio().encabezadoMovimientos(parametros, usuario);
                //strNumeroCuentaTdc
                this.GuardarLog(usuario.getLogin(),"3","1","9",data.getParametros().get(0),usuario.getIP(),"Transacciones x Facturar");
                codigo = "0";
            }else{
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                    setMensaje("El número de tarjeta de crédito no coincide con los asignados a su cuenta, por favor inserte un número de tarjeta de crédito válido ");
                else
                    setMensaje("The credit card  number does not match those assigned to your account, please insert a valid credit card number ");
                codigo = "1";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarEstatusTDCCL() throws Exception {
        final String origen = "CreditCardsAction.cargarEstatusTDCCL";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        Map<String,Object> resultado = null;
        TableOd tabla = null;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(session.get("carteras").toString());
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));

            idioma = (String) session.get("idioma");
            List<ContentSelectOd> tarjetas = (List<ContentSelectOd> ) session.get("tarjetasCL");
            if(validar.validarTDC(tarjetas,data.getParametros().get(1)).equalsIgnoreCase("SI")){
                resultado = getCreditCardsServicio().cargarEstatusTDCCL(parametros, idioma, usuario);
                estatusTarjeta = resultado.get("estatus").toString();
                tabla = (TableOd) resultado.get("lista_activas");
                tablaActivas_culumnas = tabla.getContenidoTabla_culumnas();
                contenidoTablaActivas_info = tabla.getContenidoTabla_info();
                tabla = (TableOd) resultado.get("lista_historico");
                tablaHistorico_culumnas = tabla.getContenidoTabla_culumnas();
                contenidoTablaHistorico_info = tabla.getContenidoTabla_info();
                this.GuardarLog(usuario.getLogin(),"3","1","9",data.getParametros().get(1).split("_")[2],usuario.getIP(),"Consulta de Períodos de activación");
                codigo = "0";
            }else{
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                    setMensaje("El número de tarjeta de crédito no coincide con los asignados a su cuenta, por favor inserte un número de tarjeta de crédito válido ");
                else
                    setMensaje("The credit card  number does not match those assigned to your account, please insert a valid credit card number ");
                codigo = "1";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarEstatusTDCCLE() throws Exception {
        final String origen = "CreditCardsAction.cargarEstatusTDCCLE";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");

        List<String> resultado = new ArrayList<String>();
        TableOd tabla = null;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));

            idioma = (String) session.get("idioma");
            List<ContentSelectOd> tarjetas = (List<ContentSelectOd>) session.get("tarjetasCL");
            List<ContentSelectOd> motivos = (List<ContentSelectOd>) session.get("motivosCLE");
            if(validar.validarTDC(tarjetas,data.getParametros().get(1)).equalsIgnoreCase("SI")){
                resultado = getCreditCardsServicio().cargarEstatusTDCCLE(parametros, idioma, usuario);
                estatusTarjeta = resultado.get(0);
                fechaBloqueo = resultado.get(1);
                motivoBloqueo = resultado.get(2);
                setPermiteReactivar(resultado.get(3));

                if(NullFormatter.formatBlank(estatusTarjeta).equals("B") ) {
                    setMotivosCLE(getCreditCardsServicio().cargarMovitosCLE(estatusTarjeta, idioma, usuario).getContenido());
                }

                codigo = "0";

                this.GuardarLog(usuario.getLogin(),"3","1","9",data.getParametros().get(1).split("_")[2],usuario.getIP(),"Consulta estatus de la tarjeta (Bloqueo Emergencia)");
            }else{
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                    setMensaje("El número de tarjeta de crédito no coincide con los asignados a su cuenta, por favor inserte un número de tarjeta de crédito válido ");
                else
                    setMensaje("The credit card  number does not match those assigned to your account, please insert a valid credit card number ");
                codigo = "1";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }


    public String crearReglaTDCCL() throws Exception {
        final String origen = "CreditCardsAction.crearReglaTDCCL";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0)); // tarjeta credito
            parametros.add(data.getParametros().get(1)); // fecha desde
            parametros.add(data.getParametros().get(2)); // fecha hasta
            parametros.add(usuario.getLogin()); // usuario
            parametros.add(data.getParametros().get(3)); // NOC QUE ES P

            List<ContentSelectOd> tarjetas = (List<ContentSelectOd>) session.get("tarjetasCL");
            if(validar.validarTDC(tarjetas,data.getParametros().get(0)).equalsIgnoreCase("SI")){
                estatusTarjeta = getCreditCardsServicio().crearReglaTDCCL(parametros, usuario);
                if(!estatusTarjeta.equalsIgnoreCase("NO OK")){
                    this.GuardarLog(usuario.getLogin(), "24", "1", "9", data.getParametros().get(0).split("_")[2], usuario.getIP(), "Crear periodo de activacion. Cartera N° " + data.getParametros().get(0).split("_")[1]);
                    codigo = "0";
                }else
                    codigo = "1";
            }else{
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                    setMensaje("El número de tarjeta de crédito no coincide con los asignados a su cuenta, por favor inserte un número de tarjeta de crédito válido ");
                else
                    setMensaje("The credit card  number does not match those assigned to your account, please insert a valid credit card number ");
                codigo = "1";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        } catch (Exception e) {
            if(!e.getMessage().equals("NO OK")) {
                logger.error(e);
                idioma = (String) session.get("idioma");
                if (idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "El periodo de activación no pudo ser  registrado, por favor intente de nuevo. Si el error persiste contacte a su ejecutivo o asesor. ";
                else
                    mensaje = "The activation period cannot be registered, please try again. If the error persists contact your executive or advisor.";
            }
            codigo = "1";
        }
        return SUCCESS;
    }




    public String eliminarReglaTDCCL() throws Exception {
        final String origen = "CreditCardsAction.eliminarReglaTDCCL";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));

            List<ContentSelectOd> tarjetas = (List<ContentSelectOd>) session.get("tarjetasCL");
            if(validar.validarTDC(tarjetas,data.getParametros().get(1)).equalsIgnoreCase("SI")){
                estatusTarjeta = getCreditCardsServicio().eliminarReglaTDCCL(parametros, usuario);
                this.GuardarLog(usuario.getLogin(),"26","1","9",data.getParametros().get(1).split("_")[2],usuario.getIP(),"Eliminar periodo de activacion. Cartera N° "+data.getParametros().get(1).split("_")[1]);
                codigo = "0";
                mensaje=estatusTarjeta;
            }else{
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                    setMensaje("El número de tarjeta de crédito no coincide con los asignados a su cuenta, por favor inserte un número de tarjeta de crédito válido ");
                else
                    setMensaje("The credit card  number does not match those assigned to your account, please insert a valid credit card number ");
                codigo = "1";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        } catch (Exception e) {
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "El periodo de activación no pudo ser  eliminado, por favor intente de nuevo. Si el error persiste contacte a su ejecutivo o asesor.";
            else
                mensaje = "The activation period cannot be registered, please try again. If the error persists contact your executive or advisor.";

            codigo = "1";
        }
        return SUCCESS;
    }


    public String validarFecha() throws Exception {
        final String origen = "CreditCardsAction.validarFecha";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));
            parametros.add(data.getParametros().get(4));
            parametros.add("");
            parametros.add("CREAR");

            SimpleDateFormat formatoFecha;
            formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
            String fechaHoy=formatoFecha.format(new Date()).toString();
            // if (validar.restarFechaDias(parametros.get(0),fechaHoy)>=2){
            //if (validar.fechasDiferenciaEnDias(validar.aDate(fechaHoy),validar.aDate(parametros.get(0)))>=2){
            if (validar.fechasDiferenciaEnDias(validar.aDate(fechaHoy),validar.aDate(parametros.get(0)))>=0){
                //if (validar.restarFechaDias(parametros.get(1),parametros.get(0))<=30){
                if (validar.fechasDiferenciaEnDias(validar.aDate(parametros.get(0)),validar.aDate(parametros.get(1)))<=30){
                    mensaje="OK";
                } else{
                    mensaje="NO OK DIAS";
                }
            } else{
                mensaje="NO OK DIA";
            }

            if (mensaje.equalsIgnoreCase("OK")){
                mensaje = getCreditCardsServicio().validarFechas(parametros, usuario);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        } catch (Exception e) {
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }


    public String validarFechaBO() throws Exception {
        final String origen = "CreditCardsAction.validarFechaBO";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));
            parametros.add(data.getParametros().get(4));
            parametros.add("");
            parametros.add("CREAR BO");

            SimpleDateFormat formatoFecha;
            formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
            String fechaHoy=formatoFecha.format(new Date()).toString();

            // if (validar.restarFechaDias(parametros.get(0),fechaHoy)>=2){
            mensaje="OK";
            //  } else{
            //    mensaje="NO OK DIAS";
            // }

            if (mensaje.equalsIgnoreCase("OK")){
                mensaje = getCreditCardsServicio().validarFechas(parametros, usuario);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        } catch (Exception e) {
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }


    public String validarFechaEdit() throws Exception {
        final String origen = "CreditCardsAction.validarFechaEdit";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));
            parametros.add(data.getParametros().get(4));
            parametros.add(data.getParametros().get(5));
            parametros.add("EDITAR");
            parametros.add(NullFormatter.formatBlank(data.getParametros().get(6)));

            SimpleDateFormat formatoFecha;
            formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
            String fechaHoy=formatoFecha.format(new Date()).toString();

            //if (validar.restarFechaDias(parametros.get(1),fechaHoy)>=1){
            //if (validar.fechasDiferenciaEnDias(validar.aDate(fechaHoy),validar.aDate(parametros.get(0)))>=1){
            // if (validar.restarFechaDias(parametros.get(1),parametros.get(0))<=30){
            if(!"BO".equalsIgnoreCase(parametros.get(7))) {
                if (validar.fechasDiferenciaEnDias(validar.aDate(parametros.get(0)),validar.aDate(parametros.get(1)))<=30){
                    mensaje="OK";
                } else{
                    mensaje="NO OK DIAS";
                }
            }else
                mensaje="OK";

            //} else{
            //    mensaje="NO OK DIA";
            //}

            if (mensaje.equalsIgnoreCase("OK")){
                mensaje = getCreditCardsServicio().validarFechas(parametros, usuario);
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        } catch (Exception e) {
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }


    public String editarRegla() throws Exception {
        final String origen = "CreditCardsAction.editarRegla";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));
            parametros.add(data.getParametros().get(4));
            parametros.add(data.getParametros().get(5));

            mensaje = getCreditCardsServicio().editarRegla(parametros, usuario);
            this.GuardarLog(usuario.getLogin(),"25","1","9",data.getParametros().get(3),usuario.getIP(),"Modificar periodo de activacion. Cartera N° "+data.getParametros().get(1));

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        } catch (Exception e) {

            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "El periodo de activación no pudo ser  modificado, por favor intente de nuevo. Si el error persiste contacte a su ejecutivo o asesor.";
            else
                mensaje = "The activation period cannot be modified, please try again. If the error persists contact your executive or advisor.";

            codigo = "1";
        }
        return SUCCESS;
    }


    public String cargarProximoDiaHabil() throws Exception {
        final String origen = "CreditCardsAction.cargarProximoDiaHabil";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
//            parametros.add(data.getParametros().get(0));


            mensaje = getCreditCardsServicio().cargarProximoDiaHabil(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        } catch (Exception e) {

            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }


    public String cargarFeriados() throws Exception {
        final String origen = "CreditCardsAction.cargarFeriados";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        Map<String,Object> resultado = null;
        TableOd tabla = null;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            SimpleDateFormat formatoFecha;
            formatoFecha=new SimpleDateFormat("yyyy");
            String anio=formatoFecha.format(new Date()).toString();


            feriados=getCreditCardsServicio().cargarFeriados(anio, usuario);
            mensaje="OK";

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            idioma = (String) session.get("idioma");
            mensaje="NO OK";
        }
        return SUCCESS;
    }


    public String cargarHistoricoPagosTDC() throws Exception {
        final String origen = "CreditCardsAction.cargarHistoricoPagosTDC";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        Map<String,Object> resultado = null;
        try{

//            Socket cliente;
//            DataOutputStream outStr;
//            DataInputStream inStr;
//            String datos;
//            try {
//                cliente = new Socket("192.168.223.46", 8500);
//                outStr = new DataOutputStream(cliente.getOutputStream());
//                inStr = new DataInputStream(cliente.getInputStream());
//                outStr.writeUTF("0800"+"|"+usuario.getLogin());
//                String respuesta = "";
//                respuesta = inStr.readUTF();
//                System.out.println(respuesta);
//                inStr.close();
//                outStr.close();
//                cliente.close();
//            } catch (IOException ex) {
//
//            }


            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0).split("_")[1]);
            parametros.add(data.getParametros().get(0).split("_")[0]);
            parametros.add(data.getParametros().get(0).split("_")[2]);
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add("C");

            idioma = (String) session.get("idioma");

           /* TableOd tabla2 = getCreditCardsServicio().consultarHistorioPagoTDC(parametros, idioma, usuario);
            tablaActivas_culumnas=tabla2.getContenidoTabla_culumnas();
            contenidoTablaActivas_info=tabla2.getContenidoTabla_info(); */


            parametros.set(5,"");

            TableOd tabla = getCreditCardsServicio().consultarHistorioPagoTDC(parametros, idioma, usuario);
            contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tabla.getContenidoTabla_info();

            /*if (data.getParametros().get(3).equalsIgnoreCase("OK")){
                detalles=getCreditCardsServicio().consultarDetallesEstadoCuentaCorteTDC(parametros, usuario);
                if (detalles.get(0).equalsIgnoreCase("")){
                    mensaje="NO OK";
                }
            }else{
                detalles=new ArrayList<String>();
                detalles.add("");
                detalles.add("");
                detalles.add("");
                detalles.add("");
                if (detalles.get(0).equalsIgnoreCase("")){
                    mensaje="NO OK 2";
                }
            }*/


            this.GuardarLog(usuario.getLogin(),"3","1","9",data.getParametros().get(0).split("_")[2],usuario.getIP(),"Modulo Pago de tarjetas" );

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarDatosPagosTDC() throws Exception {
        final String origen = "CreditCardsAction.cargarDatosPagosTDC";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        Map<String,Object> resultado = null;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0).split("_")[1]);
            parametros.add(data.getParametros().get(0).split("_")[0]);
            parametros.add(data.getParametros().get(0).split("_")[2]);
            parametros.add(data.getParametros().get(0).split("_")[4]);

            idioma = (String) session.get("idioma");

            if (data.getParametros().get(1).equalsIgnoreCase("OK")){
                detalles=getCreditCardsServicio().consultarDetallesEstadoCuentaCorteTDC(parametros, usuario);
                if (detalles.get(0).equalsIgnoreCase("")){
                    mensaje="NO OK";
                }
            }else{
                detalles=new ArrayList<String>();
                detalles.add("");
                detalles.add("");
                detalles.add("");
                detalles.add("");
                if (detalles.get(0).equalsIgnoreCase("")){
                    mensaje="NO OK 2";
                }
            }


            this.GuardarLog(usuario.getLogin(),"3","1","9",data.getParametros().get(0).split("_")[2],usuario.getIP(),"Modulo Pago de tarjetas" );

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }

    public String consultarPagosTdcGeneral() throws Exception {
        final String origen = "CreditCardsAction.consultarPagosTdcGeneral";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        Map<String,Object> resultado = null;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String carteras = (String) session.get("carteras");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(carteras);
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));

            //parametros.add("C");

            idioma = (String) session.get("idioma");


            TableOd tabla = getCreditCardsServicio().consultarHistorioPagoTDC(parametros, idioma, usuario);
            contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tabla.getContenidoTabla_info();

            if (data.getParametros().get(3).equalsIgnoreCase("OK")){
                detalles=getCreditCardsServicio().consultarDetallesEstadoCuentaCorteTDC(parametros, usuario);
                if (detalles.get(0).equalsIgnoreCase("")){
                    mensaje="NO OK";
                }
            }else{
                detalles=new ArrayList<String>();
                detalles.add("");
                detalles.add("");
                detalles.add("");
                detalles.add("");
                if (detalles.get(0).equalsIgnoreCase("")){
                    mensaje="NO OK 2";
                }
            }


            this.GuardarLog(usuario.getLogin(),"3","1","9",data.getParametros().get(0).split("_")[2],usuario.getIP(),"Modulo Pago de tarjetas" );




            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }

    public String guardarPagoTDC() throws Exception {
        final String origen = "CreditCardsAction.guardarPagoTDC";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        Map<String,Object> resultado = null;
        ParametrosPersonalesOd parametrosPersonales = new ParametrosPersonalesOd();
        idioma = (String) session.get("idioma");
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();

            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));
            parametros.add(data.getParametros().get(4));
            parametros.add(data.getParametros().get(5));
            parametros.add(data.getParametros().get(6).replace(",",""));

            parametrosPersonales = (ParametrosPersonalesOd) session.get("parametrosPersonales");

            mensaje=getCreditCardsServicio().guardarPagoTDC(parametros, usuario, parametrosPersonales,idioma);

            this.GuardarLog(usuario.getLogin(),"28","1","9",data.getParametros().get(2),usuario.getIP(),"Registro de pago de TDC" );

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";

            getCreditCardsServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
        }
        return SUCCESS;
    }


    public String detallePagoTDC() throws Exception {
        final String origen = "CreditCardsAccion.detallePagoTDC";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        List<String> parametros;

        idioma = (String) session.get("idioma");

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + CreditCardsAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));

            usuario = (VBTUsersOd) session.get("user");

            this.setDetalle_pagoTDC(getCreditCardsServicio().consultarPagosTdc_detalle_fc(parametros, usuario, idioma));


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + CreditCardsAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            getCreditCardsServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public String cambiarEstatusPagosTdc() throws Exception {
        final String origen = "CreditCardsAccion.cambiarEstatusPagosTdc";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        String numPago;
        String status;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + CreditCardsAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            numPago = data.getParametros().get(0);
            status = data.getParametros().get(1);

            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");

            respuesta = getCreditCardsServicio().cambiarEstatusPagosTDC(status, numPago, usuario, idioma);


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + CreditCardsAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            getCreditCardsServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String validarLimiteSalvis() throws Exception {
        final String origen = "CreditCardsAction.validarLimiteSalvis";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");

        TableOd tabla = null;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);

            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));

            mensaje = getCreditCardsServicio().validarLimiteSalvis(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            idioma = (String) session.get("idioma");
            mensaje="NO OK";
        }
        return SUCCESS;
    }




    public CreditCardsOd getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardsOd creditCard) {
        this.creditCard = creditCard;
    }

    public ICreditCardsServicio getCreditCardsServicio() {
        return creditCardsServicio;
    }

    public void setCreditCardsServicio(ICreditCardsServicio creditCardsServicio) {
        this.creditCardsServicio = creditCardsServicio;
    }

    public Map getSession() {
        return session;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "CreditCardsAction.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getCreditCardsServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return respuesta;
    }

    public String cambiarEstatusCLE() throws Exception {
        final String origen = "CreditCardsAccion.cambiarEstatusCLE";
        long time;
        List<String> parametros;
        VBTUsersOd usuario = new VBTUsersOd();
        validacionUtil validar = new validacionUtil();
        String numPago;
        String status;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + CreditCardsAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));
            parametros.add(data.getParametros().get(4));
            parametros.add(data.getParametros().get(5));
            parametros.add(data.getParametros().get(6));   //Accion
            parametros.add(data.getParametros().get(7));   //DescripcionMotivo

            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");

            if(parametros.get(6).equalsIgnoreCase("BLQ"))
                respuesta = getCreditCardsServicio().cambiarEstatusCLE(parametros, usuario, idioma,true);
            else
                respuesta = getCreditCardsServicio().cambiarEstatusCLE(parametros, usuario, idioma,false);

            codigo = "0";

            this.GuardarLog(usuario.getLogin(),"6","1","9",parametros.get(2),usuario.getIP(),"Cambiar Estatus TDC - " + (parametros.get(6).equalsIgnoreCase("BLQ")? "Bloqueo":"Solicita Desbloqueo") );

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);


        } catch (Exception e) {
            logger.error(e);
            getCreditCardsServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            setMensaje(e.getMessage());
            respuesta = "ERROR";
        }
        return SUCCESS;
    }

    public String cargarTarjetasCLE() throws Exception {
        final String origen = "CreditCardsAction.cargarTarjetasCLE";
        long time;
        List<String> parametros;
        String carteras= new String();
        SelectOd tarjeta = new SelectOd();
        VBTUsersOd usersOd = null;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");
            usersOd = (VBTUsersOd) session.get("user");
            tarjeta=getCreditCardsServicio().cargarTarjetasTDCCLE(carteras, idioma, usersOd);
            tarjetasCL=tarjeta.getContenido();
            session.put("tarjetasCL",tarjetasCL);

            if(tarjetasCL == null || tarjetasCL.size()==0)
                codigo = "1";
            else
                codigo = "0";

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e,e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
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

    public void setSession(Map<String, Object> session) {
        //To change body of implemented methods use File | Settings | File Templates.
        this.session = session;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public DataJson getData() {
        return data;
    }

    public void setData(DataJson data) {
        this.data = data;
    }

    public String getJsonCreditCards() {
        return jsonCreditCards;
    }

    public void setJsonCreditCards(String jsonCreditCards) {
        this.jsonCreditCards = jsonCreditCards;
    }

    public List<ContentSelectOd> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<ContentSelectOd> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public List<ContentSelectOd> getFecha() {
        return fecha;
    }

    public void setFecha(List<ContentSelectOd> fecha) {
        this.fecha = fecha;
    }

    public List<String> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<String> detalles) {
        this.detalles = detalles;
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

    public List<ContentSelectOd> getTarjetasITT() {
        return tarjetasITT;
    }

    public void setTarjetasITT(List<ContentSelectOd> tarjetasITT) {
        this.tarjetasITT = tarjetasITT;
    }

    public List<ContentSelectOd> getTarjetasCL() {
        return tarjetasCL;
    }

    public void setTarjetasCL(List<ContentSelectOd> tarjetasCL) {
        this.tarjetasCL = tarjetasCL;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstatusTarjeta() {
        return estatusTarjeta;
    }

    public void setEstatusTarjeta(String estatusTarjeta) {
        this.estatusTarjeta = estatusTarjeta;
    }

    public List<ContentsTableColumnsOd> getTablaActivas_culumnas() {
        return tablaActivas_culumnas;
    }

    public void setTablaActivas_culumnas(List<ContentsTableColumnsOd> tablaActivas_culumnas) {
        this.tablaActivas_culumnas = tablaActivas_culumnas;
    }

    public List<ContentsTableColumnsOd> getTablaHistorico_culumnas() {
        return tablaHistorico_culumnas;
    }

    public void setTablaHistorico_culumnas(List<ContentsTableColumnsOd> tablaHistorico_culumnas) {
        this.tablaHistorico_culumnas = tablaHistorico_culumnas;
    }

    public List<ContentTableInfoOd> getContenidoTablaActivas_info() {
        return contenidoTablaActivas_info;
    }

    public void setContenidoTablaActivas_info(List<ContentTableInfoOd> contenidoTablaActivas_info) {
        this.contenidoTablaActivas_info = contenidoTablaActivas_info;
    }

    public List<ContentTableInfoOd> getContenidoTablaHistorico_info() {
        return contenidoTablaHistorico_info;
    }

    public void setContenidoTablaHistorico_info(List<ContentTableInfoOd> contenidoTablaHistorico_info) {
        this.contenidoTablaHistorico_info = contenidoTablaHistorico_info;
    }

    public List<List<String>> getFeriados() {
        return feriados;
    }

    public void setFeriados(List<List<String>> feriados) {
        this.feriados = feriados;
    }

    public List<ContentsTableColumnsOd> getContenidoTablaDetalle_culumnas() {
        return contenidoTablaDetalle_culumnas;
    }

    public void setContenidoTablaDetalle_culumnas(List<ContentsTableColumnsOd> contenidoTablaDetalle_culumnas) {
        this.contenidoTablaDetalle_culumnas = contenidoTablaDetalle_culumnas;
    }

    public List<ContentTableInfoOd> getContenidoTablaDetalle_info() {
        return contenidoTablaDetalle_info;
    }

    public void setContenidoTablaDetalle_info(List<ContentTableInfoOd> contenidoTablaDetalle_info) {
        this.contenidoTablaDetalle_info = contenidoTablaDetalle_info;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<ContentSelectOd> getCuentasTOB() {
        return cuentasTOB;
    }

    public void setCuentasTOB(List<ContentSelectOd> cuentasTOB) {
        this.cuentasTOB = cuentasTOB;
    }

    public List<ContentSelectOd> getTipoPagoTDC() {
        return tipoPagoTDC;
    }

    public void setTipoPagoTDC(List<ContentSelectOd> tipoPagoTDC) {
        this.tipoPagoTDC = tipoPagoTDC;
    }

    public List<String> getDetalle_pagoTDC() {
        return detalle_pagoTDC;
    }

    public void setDetalle_pagoTDC(List<String> detalle_pagoTDC) {
        this.detalle_pagoTDC = detalle_pagoTDC;
    }

    public String getGenerica() {
        return generica;
    }

    public void setGenerica(String generica) {
        this.generica = generica;
    }

    public List<ContentSelectOd> getTarjetasCLE() {
        return tarjetasCLE;
    }

    public void setTarjetasCLE(List<ContentSelectOd> tarjetasCLE) {
        this.tarjetasCLE = tarjetasCLE;
    }

    public List<ContentSelectOd> getMotivosCLE() {
        return motivosCLE;
    }

    public void setMotivosCLE(List<ContentSelectOd> motivosCLE) {
        this.motivosCLE = motivosCLE;
    }

    public String getFechaBloqueo() {
        return fechaBloqueo;
    }

    public void setFechaBloqueo(String fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }

    public String getMotivoBloqueo() {
        return motivoBloqueo;
    }

    public void setMotivoBloqueo(String motivoBloqueo) {
        this.motivoBloqueo = motivoBloqueo;
    }

    public String getPermiteReactivar() {
        return permiteReactivar;
    }

    public void setPermiteReactivar(String permiteReactivar) {
        this.permiteReactivar = permiteReactivar;
    }
}





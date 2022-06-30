package ve.com.vbtonline.vista.action.transfers;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.venezolano.util.PageConfig;
import com.venezolano.util.mail.MailManager;
import com.venezolano.util.text.NullFormatter;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.desingBank.DesingBankServicio;
import ve.com.vbtonline.servicio.negocio.desingBank.IDesingBankServicio;
import ve.com.vbtonline.servicio.negocio.timeDeposits.ITimeDepositsServicio;
import ve.com.vbtonline.servicio.negocio.transfers.ITransfersServicio;
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
public class TransfersAction extends ActionSupport implements ServletContextAware, SessionAware, Serializable {
    private static final Logger logger = Logger.getLogger(TransfersAction.class);
    private FabricaTo fabrica;
    private TransfersOd transfers;
    private ITransfersServicio transfersServicio;
    private IDesingBankServicio desingBankServicio;
    private String mensaje;
    private String code;
    private Integer existeBen;
    private Integer existeInt;
    private DataJson data = new DataJson();
    private String jsonTransfers;
    private String jsonTransfers_ValidarCuenta_TOC;
    private Map session;
    private List<ContentSelectOd> cuentas;
    private List<ContentSelectOd> Paises;
    private List<ContentSelectOd> PaisesBeneficiario;
    private List<ContentSelectOd> codBankBen;
    private List<ContentSelectOd> cuentasTOB;
    private List<ContentTemplateOd> listaTemplates;
    private SummaryTransfersToOtherBanksOd resumenTOB;
    private String fechaCierre;
    private String moneda;
    private List<String> monedas;
    private List<String> detalle_transferencia;
    private String monedaTOC;
    private String respuesta;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasTest;
    private List<ContentTableInfoOd> contenidoTabla_infoTest;
    private SummaryTransfersToOtherBanksOd datos;
    private String TAGMsgInfoNombreBeneficiario;
    private String claveValida;
    private String numref;
    private String idioma;
    private String banco;
    private String encontro;
    private String numReferencia;
    private String accountSelected;
    private VBTUsersOd usuario;
    private String montoInternas;
    private String montoExternas;
    private String cantidadInternas;
    private String cantidadExternas;

    public TransfersAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }


    public String execute() throws Exception {
        final String origen = "transfersAction.execute";
        long time;
        TransfersOd transfers;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
        }
        return INPUT;
    }


    public String cargar() throws Exception {
        final String origen = "transfersAccion.cargar";
        long time;
        TransfersOd transfers;
        String carteras;
        SelectOd cuentas2, Paises2, Paises3, codBankBen2, cuentasTOB2 = new SelectOd();
        TemplateOd template = new TemplateOd();
        CuentasOd cuentasDescrip = new CuentasOd();
        VBTUsersOd usuario = new VBTUsersOd();
        ParametrosPersonalesOd parametrosPersonales = new ParametrosPersonalesOd();
        List<String> resultadosParametros=null;
        String respuestaAux="OK";
        String origenConsulta="TOB";//Parametros Personales
//        SelectOd cuentas= new SelectOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();


            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");
            usuario = (VBTUsersOd)session.get("user");

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            parametrosPersonales = (ParametrosPersonalesOd) session.get("parametrosPersonales");
            accountSelected= parametrosPersonales.getAccount_num();
            origenConsulta=data.getParametros().get(0);
            if ((usuario.getTipo().equalsIgnoreCase("6"))||(usuario.getTipo().equalsIgnoreCase("7"))||(usuario.getTipo().equalsIgnoreCase("8"))){
                resultadosParametros= new ArrayList<>();
                resultadosParametros.add("OK");
            }else{
                resultadosParametros=getTransfersServicio().validarParametrosPersonales(parametrosPersonales.getCodpercli(),"0","TOB", parametrosPersonales.getNum_contrato(),"C");
            }

            respuesta="1";
            //TMP indica que el origen es template
            if (!origenConsulta.equalsIgnoreCase("TMP")){
                respuestaAux=resultadosParametros.get(0);
            }else if(resultadosParametros.get(0).equalsIgnoreCase("NO OK")){
                if(idioma.equalsIgnoreCase("_ve_es"))
                    respuesta= ResourceBundle.getBundle("vbtonline_ve_es").getString(resultadosParametros.get(1));
                else
                    respuesta= ResourceBundle.getBundle("vbtonline_us_en").getString(resultadosParametros.get(1));
            }
            if(respuestaAux.equalsIgnoreCase("OK")){
                    cuentas2 = getTransfersServicio().cargarCuentasTransOtrosBan(carteras);
                    cuentas = cuentas2.getContenido();
                    session.put("cuentas", cuentas);


                    Paises2 = getTransfersServicio().cargarPaises();
                    Paises = Paises2.getContenido();
                    session.put("Paises", Paises);

                    Paises3 = getTransfersServicio().cargarPaisesBeneficiario();
                    PaisesBeneficiario = Paises3.getContenido();
                    session.put("PaisesBeneficiario", PaisesBeneficiario);


                    codBankBen2 = getTransfersServicio().cargarCodBankBen();
                    codBankBen = codBankBen2.getContenido();
                    session.put("codBankBen", codBankBen);


                    carteras = (String) session.get("carteras");
                    cuentasDescrip = getTransfersServicio().cargarCuentas(carteras, idioma);
                    cuentasTOB = cuentasDescrip.getContenido();
                    fechaCierre = cuentasDescrip.getFecha();
                    moneda = cuentasDescrip.getMoneda();
                    session.put("cuentasTOB", cuentasTOB);
                    session.put("fechaCierre", fechaCierre);
                    session.put("moneda", moneda);


                    usuario = (VBTUsersOd) session.get("user");
                    this.usuario = usuario;
                    template = getTransfersServicio().cargarListaTemplates(usuario.getLogin());
                    listaTemplates = new ArrayList<>();
                    listaTemplates = template.getContenido();

                    idioma = (String) session.get("idioma");

                    TAGMsgInfoNombreBeneficiario = ResourceBundle.getBundle("Transferencias" + idioma).getString("TAGMsgInfoNombreBeneficiario");
                    mensaje="OK";
            }else{

                if(idioma.equalsIgnoreCase("_ve_es"))
                    respuesta= ResourceBundle.getBundle("vbtonline_ve_es").getString(resultadosParametros.get(1));
                else
                    respuesta= ResourceBundle.getBundle("vbtonline_us_en").getString(resultadosParametros.get(1));
                code = "1";
                mensaje="NO OK";
            }

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String consultarDirectorio() throws Exception {
        final String origen = "transfersAccion.consultarDirectorio";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            TableOd tableOd = getTransfersServicio().consultarDirectorio(usuario,idioma);
            contenidoTabla_culumnasTest = tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest = tableOd.getContenidoTabla_info();


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String consultarDirectorioTransfer() throws Exception {
        final String origen = "transfersAccion.consultarDirectorioTransfer";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            TableOd tableOd = getTransfersServicio().consultarDirectorioTransfer(usuario,idioma);
            contenidoTabla_culumnasTest = tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest = tableOd.getContenidoTabla_info();


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String borrarDirectorio() throws Exception {
        final String origen = "transfersAccion.borrarDirectorio";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            String codigoTemplate = data.getParametros().get(0);
            usuario = (VBTUsersOd) session.get("user");
            idioma = (String)  session.get("idioma");
            respuesta = getTransfersServicio().borrarDirectorio(usuario.getLogin(), codigoTemplate);



            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);
            this.GuardarLog(usuario.getLogin(),"16","1","14",codigoTemplate,usuario.getIP(), "Borrar Template");


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarDetalleDirectorio() throws Exception {
        final String origen = "transfersAccion.cargarDetalleDirectorio";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        SummaryTransfersToOtherBanksOd transfers= new SummaryTransfersToOtherBanksOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            String codigoTemplate = data.getParametros().get(0);
            usuario = (VBTUsersOd) session.get("user");
            datos = new SummaryTransfersToOtherBanksOd();
            idioma = (String) session.get("idioma");

            datos = getTransfersServicio().cargarDetalleDirectorio(usuario.getLogin(), codigoTemplate);


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String editarDetalleDirectorio() throws Exception {
        final String origen = "transfersAccion.editarDetalleDirectorio";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        SummaryTransfersToOtherBanksOd transfers= new SummaryTransfersToOtherBanksOd();
        String encontro = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            validacionUtil validar = new validacionUtil();
            data = gson.fromJson(validar.caracteresEspeciales(this.jsonTransfers), DataJson.class);

            transfers = data.getResumenTOBs().get(0);
            resumenTOB = transfers;
            String codigoTemplate = resumenTOB.getNombreTemplate();
            usuario = (VBTUsersOd) session.get("user");

            encontro = getTransfersServicio().buscarTemplateEditar(resumenTOB, usuario, Integer.parseInt(codigoTemplate));

            if(encontro.equalsIgnoreCase("1")){
                respuesta = getTransfersServicio().editarTemplate(resumenTOB,usuario,codigoTemplate);
            }

//             respuesta = "OK";
            time = System.currentTimeMillis() - time;
            this.GuardarLog(usuario.getLogin(),"4","1","10",resumenTOB.getNombreTemplate(),usuario.getIP(), "Editar Template");
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String salvarNuevoDirectorio() throws Exception {
        final String origen = "transfersAccion.salvarNuevoDirectorio";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        SummaryTransfersToOtherBanksOd transfers= new SummaryTransfersToOtherBanksOd();
        encontro = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
           // data = gson.fromJson(this.jsonTransfers, DataJson.class);

            validacionUtil validar = new validacionUtil();
            data = gson.fromJson(validar.caracteresEspeciales(this.jsonTransfers), DataJson.class);

            transfers = data.getResumenTOBs().get(0);
            resumenTOB = transfers;
            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");

            encontro = getTransfersServicio().buscarTemplate(resumenTOB,usuario);

            String nombreTemplate=validar.caracteresEspeciales(resumenTOB.getNombreTemplate());


            resumenTOB.setNombreTemplate(nombreTemplate);

            this.GuardarLog(usuario.getLogin(),"12","1","10",resumenTOB.getNombreTemplate(),usuario.getIP(), "Salvar Template");
            if(encontro.equalsIgnoreCase("1")){
                respuesta = getTransfersServicio().salvarTemplate(resumenTOB,usuario);
            }else{
                respuesta = "NO OK";
            }

//             respuesta = "OK";
            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String transferenciasPorAprobar() throws Exception {
        final String origen = "transfersAccion.transferenciasPorAprobar";
        long time;
        TransfersOd transfers;
        String carteras;
        VBTUsersOd usuario = new VBTUsersOd();
        SelectOd cuentas2, Paises2, codBankBen2, cuentasTOB2 = new SelectOd();
        CuentasOd cuentasDescrip = new CuentasOd();
//        SelectOd cuentas= new SelectOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();


            carteras = (String) session.get("carteras");
            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            TableOd tableOd = getTransfersServicio().consultarTransferencias(carteras, usuario.getNumContrato(), "C", idioma);
            contenidoTabla_culumnasTest = tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest = tableOd.getContenidoTabla_info();


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public String detalle_transferencia() throws Exception {
        final String origen = "transfersAccion.transferenciasPorLiberar";
        long time;
        String carteras;
        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();


            carteras = (String) session.get("carteras");
            usuario = (VBTUsersOd) session.get("user");
            detalle_transferencia= getTransfersServicio().consultTransfers_detalle_fc(carteras, usuario.getNumContrato(),jsonTransfers);


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public String transferenciasPorLiberar() throws Exception {
        final String origen = "transfersAccion.transferenciasPorLiberar";
        long time;
        String carteras;
        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();


            carteras = (String) session.get("carteras");
            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            TableOd tableOd = getTransfersServicio().consultarTransferencias(carteras, usuario.getNumContrato(), "A", idioma);
            contenidoTabla_culumnasTest = tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest = tableOd.getContenidoTabla_info();


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cambiarEstatusTransferenciaAprobar() throws Exception {
        final String origen = "transfersAccion.cambiarEstatusTransferenciaAprobar";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            String numRef = data.getParametros().get(0);
            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");

            respuesta = getTransfersServicio().cambiarEstatusTransferencia("A", numRef, usuario,idioma);

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public String cambiarEstatusTransferenciaRechazar() throws Exception {
        final String origen = "transfersAccion.cambiarEstatusTransferenciaRechazar";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            String numRef = data.getParametros().get(0);
            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            respuesta = getTransfersServicio().cambiarEstatusTransferencia("D", numRef, usuario,idioma);


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }




    public String cambiarEstatusTransferenciaLiberar() throws Exception {
        final String origen = "transfersAccion.cambiarEstatusTransferenciaLiberar";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            String numRef = data.getParametros().get(0);
            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            respuesta = getTransfersServicio().cambiarEstatusTransferencia("L", numRef, usuario,idioma);


           /* if (respuesta.equalsIgnoreCase("OK")) {
                respuesta = getTransfersServicio().saveToOtherBankJS_BOFA(numRef);
                if (respuesta.equalsIgnoreCase("OK")) {
                    mensaje = "La transacción ha sido registrada en BOFA exitosamente";
                } else
                    mensaje = "Error al registrar la transaccion en BOFA";
            } */

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarToOtherClient() throws Exception {
        final String origen = "transfersAccion.cargarToOtherClient";
        long time;
        TransfersOd transfers;
        String carteras;
        SelectOd cuentas2, Paises2, codBankBen2, cuentasTOB2 = new SelectOd();
        CuentasOd cuentasDescrip = new CuentasOd();
        ParametrosPersonalesOd parametrosPersonales = new ParametrosPersonalesOd();
        List<String> resultadosParametros=null;
        VBTUsersOd usu = new VBTUsersOd();
//        SelectOd cuentas= new SelectOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

//            carteras = "cartera";  //obtengo parametro de session
                carteras = (String) session.get("carteras");
                idioma = (String) session.get("idioma");
                parametrosPersonales = (ParametrosPersonalesOd) session.get("parametrosPersonales");

            usu = (VBTUsersOd) session.get("user");

                if ((usu.getTipo().equalsIgnoreCase("6"))||(usu.getTipo().equalsIgnoreCase("7"))||(usu.getTipo().equalsIgnoreCase("8"))){
                    resultadosParametros= new ArrayList<>();
                    resultadosParametros.add("OK");
                }else{
                    resultadosParametros=getTransfersServicio().validarParametrosPersonales(parametrosPersonales.getCodpercli(),"0","TIN", parametrosPersonales.getNum_contrato(),"C");
                }



                     if(resultadosParametros.get(0).equalsIgnoreCase("OK")){

                        cuentasDescrip = getTransfersServicio().cargarCuentas(carteras,idioma);
                        cuentasTOB = cuentasDescrip.getContenido();
                        fechaCierre = cuentasDescrip.getFecha();
                        moneda = cuentasDescrip.getMoneda();
                        usuario = (VBTUsersOd) session.get("user");
                        session.put("cuentasTOB", cuentasTOB);
                        session.put("fechaCierre", fechaCierre);
                        session.put("moneda", moneda);

                        mensaje="OK";
                     }else{

                         if(idioma.equalsIgnoreCase("_ve_es"))
                             respuesta= ResourceBundle.getBundle("vbtonline_ve_es").getString(resultadosParametros.get(1));
                         else
                             respuesta= ResourceBundle.getBundle("vbtonline_us_en").getString(resultadosParametros.get(1));
                         mensaje="NO OK";
                     }

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarTranEntreMisCuentas() throws Exception {
        final String origen = "transfersAccion.cargarTranEntreMisCuentas";
        long time;
        TransfersOd transfers;
        String carteras;
        SelectOd cuentas2, Paises2, codBankBen2, cuentasTOB2 = new SelectOd();
        CuentasOd cuentasDescrip = new CuentasOd();
        ParametrosPersonalesOd parametrosPersonales = new ParametrosPersonalesOd();
        List<String> resultadosParametros=null;
//        SelectOd cuentas= new SelectOd();
        String respuestaAux="OK";
        String origenConsulta="OTHER";//Parametros Personales
        VBTUsersOd usu=new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();



            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            origenConsulta=data.getParametros().get(0);
//            carteras = "cartera";  //obtengo parametro de session
                carteras = (String) session.get("carteras");
                idioma = (String) session.get("idioma");
                parametrosPersonales = (ParametrosPersonalesOd) session.get("parametrosPersonales");


                accountSelected = parametrosPersonales.getAccount_num();
                 if (!origenConsulta.equalsIgnoreCase("PP")){
                     usu = (VBTUsersOd) session.get("user");
                     if ((usu.getTipo().equalsIgnoreCase("6"))||(usu.getTipo().equalsIgnoreCase("7"))||(usu.getTipo().equalsIgnoreCase("8"))){
                         resultadosParametros= new ArrayList<>();
                         resultadosParametros.add("OK");
                         respuestaAux="OK";
                     }else{
                         resultadosParametros=getTransfersServicio().validarParametrosPersonales(parametrosPersonales.getCodpercli(),"0","TIN", parametrosPersonales.getNum_contrato(),"C");
                         respuestaAux=resultadosParametros.get(0);
                     }

                 }


                cuentasDescrip = getTransfersServicio().cargarCuentas(carteras,idioma);

                if(respuestaAux.equalsIgnoreCase("OK")){
                    cuentasTOB = cuentasDescrip.getContenido();
                    fechaCierre = cuentasDescrip.getFecha();
                    moneda = cuentasDescrip.getMoneda();
                    monedas = cuentasDescrip.getMonedas();
                    usuario = (VBTUsersOd) session.get("user");
                    session.put("cuentasTOB", cuentasTOB);
                    session.put("fechaCierre", fechaCierre);
                    session.put("moneda", moneda);
                    session.put("monedas",monedas);
                respuesta="OK";
                mensaje="OK";
              }else{
                    if(idioma.equalsIgnoreCase("_ve_es"))
                        respuesta= ResourceBundle.getBundle("vbtonline_ve_es").getString(resultadosParametros.get(1));
                    else
                        respuesta= ResourceBundle.getBundle("vbtonline_us_en").getString(resultadosParametros.get(1));
                    mensaje="NO OK";
                }

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public String obtenerMontosLiberadorFC() throws Exception {
        final String origen = "transfersAccion.obtenerMontosLiberadorFC";
        long time;
        String respuestaAux="OK";
        String tipoTranferencia="";
        List<String> resultadosParametros=null;
        try {


            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            VBTUsersOd user = new VBTUsersOd();
            user = (VBTUsersOd) session.get("user");


            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
//            carteras = "cartera";  //obtengo parametro de session

            idioma = (String) session.get("idioma");


              resultadosParametros=getTransfersServicio().obtenerMontosLiberadorFC(user.getNumContrato());

               montoInternas=resultadosParametros.get(0);
               montoExternas=resultadosParametros.get(1);
               cantidadInternas=resultadosParametros.get(2);
               cantidadExternas=resultadosParametros.get(3);


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }





    public String validateToOtherBanks() throws Exception {
        final String origen = "transfersAccion.validateToOtherBanks";
        long time;
        SummaryTransfersToOtherBanksOd transfers;
        String carteras;

        SelectOd cuentas2, Paises2, codBankBen2 = new SelectOd();
//        SelectOd cuentas= new SelectOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
//            ctaOpcOd= getBackOfficeServicio().opcionesGrupo("", );
            idioma = (String) session.get("idioma");

            transfers = data.getResumenTOBs().get(0);
            resumenTOB = transfers;

            existeBen = getTransfersServicio().validarBancoBeneficiario_Intermediario(transfers.getBeneficiaryBankCodeNumber(),
                    transfers.getBeneficiaryBankCodeType(),
                    transfers.getBeneficiaryBankCountryCode(),
                    null, null, null);

            if ((!transfers.getIntermediaryBankCodeType().equals(null))&& (!transfers.getIntermediaryBankCodeNumber().equals(null))&&( transfers.getIntermediaryBankCountryCode().equals(null))){
                existeInt = getTransfersServicio().validarBancoBeneficiario_Intermediario(null, null, null,
                        transfers.getIntermediaryBankCodeType(),
                        transfers.getIntermediaryBankCodeNumber(),
                        transfers.getIntermediaryBankCountryCode());
            }else{
                existeInt=1;
            }

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String validarCuentaDestino_TOC() throws Exception {
        final String origen = "transfersAccion.validarCuentaDestino_TOC";
        long time;
        SummaryTransfersToOtherBanksOd transfers;
        String cuenta = new String();
        String result = "input";

        SelectOd cuentas2, Paises2, codBankBen2 = new SelectOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers_ValidarCuenta_TOC, DataJson.class);
            idioma = (String) session.get("idioma");
            cuenta = data.getParametros().get(0);
            respuesta = "NO OK";
            monedaTOC = getTransfersServicio().validarCuentaDestino_TOC(cuenta);
            if (!monedaTOC.equalsIgnoreCase("")) {
                respuesta = "OK";
            }
            result = SUCCESS;

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }

    public String saveToOtherBanks() throws Exception {
        VBTUsersOd user = new VBTUsersOd();
        SummaryTransfersToOtherBanksOd resumenTOBAux= new SummaryTransfersToOtherBanksOd();
        user = (VBTUsersOd) session.get("user");
        String tipoContrato="";
        if(user.getTipo().equals("6") || user.getTipo().equals("7") || user.getTipo().equals("8") || user.getTipo().equals("9")){
            final String origen = "transfersAccion.saveToOtherBanksFC";
            long time;
            SummaryTransfersToOtherBanksOd transfers;
            String carteras;
            String respuesta = new String();
            List<String> resultados = new ArrayList<>();
            Integer exitoso;
            VBTUsersOd usuario = new VBTUsersOd();
            SelectOd cuentas2, Paises2, codBankBen2 = new SelectOd();
    //        SelectOd cuentas= new SelectOd();
            String strEmailsInternos = new String();
            MailManager mailManager = new MailManager("vbtonline");
            ParametrosPersonalesOd parametrosPersonalesFC = new ParametrosPersonalesOd();
            ParametrosPersonalesOd parametrosPersonalesBaseFC = new ParametrosPersonalesOd();
            validacionUtil validar = new validacionUtil();
            List<String> resultadosParametros;
            tipoContrato="FC";
            try {

                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

                time = System.currentTimeMillis();

               /* parametrosPersonalesFC = (ParametrosPersonalesFCOd) session.get("parametrosPersonalesFC");
                parametrosPersonalesBaseFC = (ParametrosPersonalesFCOd) session.get("parametrosPersonalesBaseFC");   */

                parametrosPersonalesFC = (ParametrosPersonalesOd) session.get("parametrosPersonales");
                parametrosPersonalesBaseFC = (ParametrosPersonalesOd) session.get("parametrosPersonalesBase");

                if (parametrosPersonalesFC == null){
                    parametrosPersonalesFC = parametrosPersonalesBaseFC;
                }

                Gson gson = new Gson();
               // data = gson.fromJson(this.jsonTransfers, DataJson.class);

                data = gson.fromJson(validar.caracteresEspeciales(this.jsonTransfers), DataJson.class);
    //            ctaOpcOd= getBackOfficeServicio().opcionesGrupo("", );

                transfers = data.getResumenTOBs().get(0);
                resumenTOB = transfers;
                resumenTOB.setMoneda((String) session.get("moneda"));

                usuario = (VBTUsersOd) session.get("user");
                idioma = (String) session.get("idioma");
                resumenTOB.setMonto_aux(resumenTOB.getAmount());
                String monto = resumenTOB.getAmount().replace(".", "");
                resumenTOB.setAmount(monto.replace(",", "."));

                carteras = (String) session.get("carteras");
                List<ContentSelectOd> cuentas = (List<ContentSelectOd> ) session.get("cuentasTOB");
                String strNumeroCuentaDebito  = resumenTOB.getAccountCode().substring(0,resumenTOB.getAccountCode().indexOf("|")).trim();
                String strCodigoCarteraDebito = resumenTOB.getAccountCode().substring((resumenTOB.getAccountCode().indexOf("|")+1),resumenTOB.getAccountCode().length()).trim();

                if(validar.validarCuentaTransfer(carteras, cuentas, strNumeroCuentaDebito, strCodigoCarteraDebito).equalsIgnoreCase("SI")){

                //    resultadosParametros=getTransfersServicio().validarParametrosPersonales(parametrosPersonalesFC.getCodpercli(),resumenTOB.getAmount(),"TOB", parametrosPersonalesFC.getNum_contrato(), tipoContrato);

                   // if(resultadosParametros.get(0).equalsIgnoreCase("OK")){

                    if(validar.validarMontosTransferenciaFC(parametrosPersonalesFC, resumenTOB, "TOB").equalsIgnoreCase("OK")){
    //                    code = "0";
                        resumenTOBAux=(SummaryTransfersToOtherBanksOd)resumenTOB.clone();
                        resultados = getTransfersServicio().saveToOtherBank_JS(resumenTOB, usuario, idioma);
                        if (resultados.get(0).equalsIgnoreCase("OK")) {


                            strNumeroCuentaDebito = resumenTOB.getAccountCode().substring(0, resumenTOB.getAccountCode().indexOf("|")).trim();
                            //strEmailsInternos = getTransfersServicio().EmailsInternos(strNumeroCuentaDebito);

                            String numRef = getTransfersServicio().numeroReferencia(resultados.get(1));
                            this.GuardarLogFC(usuario.getLogin(),"19","1","4",strNumeroCuentaDebito,usuario.getIP(), "Transferencia Externa N° ref: " + numRef ,usuario.getNumContrato());


                            mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("Transferencias" + idioma).getString("TAGMsgTituloEmailClienteFC"), resultados.get(2));
                           /* if (!NullFormatter.isBlank(strEmailsInternos)) {
                                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), strEmailsInternos, ResourceBundle.getBundle("Transferencias" + idioma).getString("TAGMsgEmailNotificacionTransferenciaExterna"), resultados.get(3));
                            }    */

                          /*  if (!NullFormatter.isBlank(ResourceBundle.getBundle("vbtonline").getString("mail_transferencias"))) {
                                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_transferencias"), ResourceBundle.getBundle("Transferencias" + idioma).getString("TAGMsgEmailNotificacionTransferenciaExterna"), resultados.get(3));
                            }      */

                            code = "0";

                            if(idioma.equalsIgnoreCase("_ve_es"))
                                mensaje = "Transaccion exitosa";
                            else
                                mensaje = "Successful transaction";
                            numref = resultados.get(1);

                            if ("FW".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                                resumenTOB.setIntermediaryBankCodeType("ABA");
                            } else if ("SA".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                                resumenTOB.setIntermediaryBankCodeType("SWIFT");
                            } else if ("CHIPS".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                                resumenTOB.setIntermediaryBankCodeType("CHIPS");
                            } else if ("".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                                resumenTOB.setIntermediaryBankCodeType("ACCOUNT");
                            }

                            if ("FW".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                                resumenTOB.setBeneficiaryBankCodeType("ABA");
                            } else if ("SA".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                                resumenTOB.setBeneficiaryBankCodeType("SWIFT");
                            } else if ("CHIPS".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                                resumenTOB.setBeneficiaryBankCodeType("CHIPS");
                            } else if ("".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                                resumenTOB.setBeneficiaryBankCodeType("ACCOUNT");
                            }
                        } else {

                            if(idioma.equalsIgnoreCase("_ve_es"))
                                mensaje = "Fallo durante el registro de la transferencia";
                            else
                                mensaje = "Failure during the transfer register";
                            code = "1";
                        }
                    }else{
                        if(idioma.equalsIgnoreCase("_ve_es"))
                            mensaje = "El monto de la transferencia excede los indicados por sus parametros personales";
                        else
                            mensaje = "The transfer amount exceeds the amounts indicated by your \"personal parameters\"";
                        code = "1";
                    }
                }else{
                    if(idioma.equalsIgnoreCase("_ve_es"))
                        mensaje = "La cuenta a debitar que selecciono no le pertenece, por favor seleccione otra cuenta";
                    else
                        mensaje = "The account you selected does not belong, please select another account";
                    code = "1";
                }


                time = System.currentTimeMillis() - time;

                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);

                resumenTOB=resumenTOBAux;

            } catch (Exception e) {
                logger.error(e);
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "Transaccion Fallida";
                else
                    mensaje = "Transaction Failed";
                code = "1";
            }
            return SUCCESS;

        }else{
                    final String origen = "transfersAccion.saveToOtherBanks";
                    long time;
                    SummaryTransfersToOtherBanksOd transfers;
                    String carteras;
                String respuesta = new String();
                List<String> resultados = new ArrayList<>();
                Integer exitoso;
                VBTUsersOd usuario = new VBTUsersOd();
                SelectOd cuentas2, Paises2, codBankBen2 = new SelectOd();
        //        SelectOd cuentas= new SelectOd();
                String strEmailsInternos = new String();
                MailManager mailManager = new MailManager("vbtonline");
                ParametrosPersonalesOd parametrosPersonales = new ParametrosPersonalesOd();
                ParametrosPersonalesOd parametrosPersonalesBase = new ParametrosPersonalesOd();
                validacionUtil validar = new validacionUtil();
                List<String> resultadosParametros;
                tipoContrato="C";
                try {

                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

                    time = System.currentTimeMillis();

                    parametrosPersonales = (ParametrosPersonalesOd) session.get("parametrosPersonales");
                    parametrosPersonalesBase = (ParametrosPersonalesOd) session.get("parametrosPersonalesBase");

                    if (parametrosPersonales == null){
                        parametrosPersonales = parametrosPersonalesBase;
                    }

                    Gson gson = new Gson();
                    data = gson.fromJson(validar.caracteresEspeciales(this.jsonTransfers), DataJson.class);
        //            ctaOpcOd= getBackOfficeServicio().opcionesGrupo("", );

                    transfers = data.getResumenTOBs().get(0);
                    resumenTOB = transfers;
                    resumenTOB.setMoneda((String) session.get("moneda"));

                    usuario = (VBTUsersOd) session.get("user");
                    idioma = (String) session.get("idioma");
                    resumenTOB.setMonto_aux(resumenTOB.getAmount());
                    String monto = resumenTOB.getAmount().replace(".", "");
                    resumenTOB.setAmount(monto.replace(",", "."));

                    carteras = (String) session.get("carteras");
                    List<ContentSelectOd> cuentas = (List<ContentSelectOd> ) session.get("cuentasTOB");
                    String strNumeroCuentaDebito  = resumenTOB.getAccountCode().substring(0,resumenTOB.getAccountCode().indexOf("|")).trim();
                    String strCodigoCarteraDebito = resumenTOB.getAccountCode().substring((resumenTOB.getAccountCode().indexOf("|")+1),resumenTOB.getAccountCode().length()).trim();

                    if(validar.validarCuentaTransfer(carteras, cuentas, strNumeroCuentaDebito, strCodigoCarteraDebito).equalsIgnoreCase("SI")){
                        //Valida Los Parametros Personales
                        resultadosParametros=getTransfersServicio().validarParametrosPersonales(parametrosPersonales.getCodpercli(),resumenTOB.getAmount(),"TOB", parametrosPersonales.getNum_contrato(),tipoContrato);

                        if(resultadosParametros.get(0).equalsIgnoreCase("OK")){
                            encontro = getTransfersServicio().buscarTemplate(resumenTOB,usuario);
                            resumenTOBAux.setStatusAprobacion(resumenTOB.getStatusAprobacion());
                            resumenTOBAux=(SummaryTransfersToOtherBanksOd)resumenTOB.clone();
                            resultados = getTransfersServicio().saveToOtherBank(resumenTOB, usuario, idioma);
                            if (resultados.get(0).equalsIgnoreCase("OK")) {


                                strNumeroCuentaDebito = resumenTOB.getAccountCode().substring(0, resumenTOB.getAccountCode().indexOf("|")).trim();
                                strEmailsInternos = getTransfersServicio().EmailsInternos(strNumeroCuentaDebito);
//                                this.GuardarLog(usuario.getLogin(),"16","1","4",strNumeroCuentaDebito,usuario.getIP(), "N° ref: " + resultados.get(1) );
                                String numRef = getTransfersServicio().numeroReferencia(resultados.get(1));
                                this.GuardarLog(usuario.getLogin(),"16","1","4",strNumeroCuentaDebito,usuario.getIP(), "Transferencia Externa N° ref: " + numRef );
                                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("Transferencias" + idioma).getString("TAGMsgTituloEmailCliente"), resultados.get(2));
                                if (!NullFormatter.isBlank(strEmailsInternos)) {
                                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), strEmailsInternos, ResourceBundle.getBundle("Transferencias" + idioma).getString("TAGMsgEmailNotificacionTransferenciaExterna"), resultados.get(3));
                                }

                                if (!NullFormatter.isBlank(ResourceBundle.getBundle("vbtonline").getString("mail_transferencias"))) {
                                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_transferencias"), ResourceBundle.getBundle("Transferencias" + idioma).getString("TAGMsgEmailNotificacionTransferenciaExterna"), resultados.get(3));
                                }

                                code = "0";

                                if(idioma.equalsIgnoreCase("_ve_es"))
                                    mensaje = "Transaccion exitosa";
                                else
                                    mensaje = "Successful transaction";
                                numref = resultados.get(1);

                                if ("FW".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                                    resumenTOB.setIntermediaryBankCodeType("ABA");
                                } else if ("SA".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                                    resumenTOB.setIntermediaryBankCodeType("SWIFT");
                                } else if ("CHIPS".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                                    resumenTOB.setIntermediaryBankCodeType("CHIPS");
                                } else if ("".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                                    resumenTOB.setIntermediaryBankCodeType("ACCOUNT");
                                }

                                if ("FW".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                                    resumenTOB.setBeneficiaryBankCodeType("ABA");
                                } else if ("SA".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                                    resumenTOB.setBeneficiaryBankCodeType("SWIFT");
                                } else if ("CHIPS".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                                    resumenTOB.setBeneficiaryBankCodeType("CHIPS");
                                } else if ("".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                                    resumenTOB.setBeneficiaryBankCodeType("ACCOUNT");
                                }
                            } else {

                                if(idioma.equalsIgnoreCase("_ve_es"))
                                    mensaje = "Fallo durante el registro de la transferencia";
                                else
                                    mensaje = "Failure during the transfer register";
                                code = "1";
                            }
                        }else{

                            if(idioma.equalsIgnoreCase("_ve_es"))
                                mensaje= ResourceBundle.getBundle("vbtonline_ve_es").getString(resultadosParametros.get(1));
                            else
                                mensaje= ResourceBundle.getBundle("vbtonline_us_en").getString(resultadosParametros.get(1));
                            code = "1";
                        }
                    }else{
                        if(idioma.equalsIgnoreCase("_ve_es"))
                            mensaje = "La cuenta a debitar que selecciono no le pertenece, por favor seleccione otra cuenta";
                        else
                            mensaje = "The account you selected does not belong, please select another account";
                        code = "1";
                    }


                    time = System.currentTimeMillis() - time;

                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


                } catch (Exception e) {
                    logger.error(e);
                    if(idioma.equalsIgnoreCase("_ve_es"))
                        mensaje = "Transaccion Fallida";
                    else
                        mensaje = "Transaction Failed";
                    code = "1";
                }
                resumenTOB=resumenTOBAux;
                return SUCCESS;
        }
    }

    public String cambiarEstatusTemplate() throws Exception {
        final String origen = "transfersAccion.cambiarEstatusTemplate";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            String id = data.getParametros().get(0);
            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");

            respuesta = getTransfersServicio().cambiarEstatusTemplate(id, usuario.getLogin(),idioma);


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public String saveToOtherClient() throws Exception {
        final String origen = "transfersAccion.saveToOtherClient";
        long time;
        SummaryTransfersToOtherBanksOd transfers;
        String carteras;
        String respuesta2 = new String();
        Integer exitoso;
        VBTUsersOd usuario = new VBTUsersOd();
        SelectOd cuentas2, Paises2, codBankBen2 = new SelectOd();
        String refDebito = new String();
        String refCredito = new String();
//        SelectOd cuentas= new SelectOd();
        this.respuesta="OK";
        ParametrosPersonalesOd parametrosPersonales= new ParametrosPersonalesOd();
        ParametrosPersonalesOd parametrosPersonalesBase= new ParametrosPersonalesOd();
        validacionUtil validar = new validacionUtil();
        List<String> resultadosParametros;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();


            parametrosPersonales = (ParametrosPersonalesOd) session.get("parametrosPersonales");
            parametrosPersonalesBase = (ParametrosPersonalesOd) session.get("parametrosPersonalesBase");

            if (parametrosPersonales == null){
                parametrosPersonales = parametrosPersonalesBase;
            }

            Gson gson = new Gson();
            data = gson.fromJson(validar.caracteresEspeciales(this.jsonTransfers), DataJson.class);
//            ctaOpcOd= getBackOfficeServicio().opcionesGrupo("", );

            transfers = data.getResumenTOBs().get(0);
            resumenTOB = transfers;
            resumenTOB.setMoneda((String) session.get("moneda"));

            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            resumenTOB.setMonto_aux(resumenTOB.getAmount());
            String monto2 = resumenTOB.getAmount().replace(".","");
            resumenTOB.setAmount(monto2.replace(",", "."));

            carteras = (String) session.get("carteras");
            List<ContentSelectOd> cuentas = (List<ContentSelectOd> ) session.get("cuentasTOB");
            String strNumeroCuentaDebito  = resumenTOB.getAccountCode().substring(0,resumenTOB.getAccountCode().indexOf("|")).trim();
            String strCodigoCarteraDebito = resumenTOB.getAccountCode().substring((resumenTOB.getAccountCode().indexOf("|")+1),resumenTOB.getAccountCode().length()).trim();

            if(validar.validarCuentaTransfer(carteras, cuentas, strNumeroCuentaDebito, strCodigoCarteraDebito).equalsIgnoreCase("SI")){
                //Valida Los Parametros Personales FC
                if ((usuario.getTipo().equalsIgnoreCase("6"))||(usuario.getTipo().equalsIgnoreCase("7"))||(usuario.getTipo().equalsIgnoreCase("8"))){
                    resultadosParametros= new ArrayList<>();
                    resultadosParametros.add("OK");
                }else{
                    resultadosParametros=getTransfersServicio().validarParametrosPersonales(parametrosPersonales.getCodpercli(),resumenTOB.getAmount(),"TIN",parametrosPersonales.getNum_contrato(),"C");
                }

                if(resultadosParametros.get(0).equalsIgnoreCase("OK")){
                    respuesta2 = getTransfersServicio().saveToOtherClient(resumenTOB, usuario,idioma);
                    if (respuesta2 != null) {
                        refDebito = respuesta2.substring((respuesta2.indexOf("|") + 1), respuesta2.indexOf("!")).trim();
                        refCredito = respuesta2.substring((respuesta2.indexOf("!") + 1), respuesta2.length()).trim();
                        respuesta2 = respuesta2.substring(0, (respuesta2.indexOf("|"))).trim();
                        numref = refDebito;
                        if (respuesta2.equalsIgnoreCase("OK")) {
                            code = "0";

                            if ((usuario.getTipo().equalsIgnoreCase("6"))||(usuario.getTipo().equalsIgnoreCase("7"))||(usuario.getTipo().equalsIgnoreCase("8"))){
                                this.GuardarLogFC(usuario.getLogin(),"19","1","4",strNumeroCuentaDebito,usuario.getIP(), "Transferencia a Otro Cliente VBT  N° ref: " + refDebito ,usuario.getNumContrato());
                            }else{
                                this.GuardarLog(usuario.getLogin(),"16","1","4",strNumeroCuentaDebito,usuario.getIP(), "N° ref: " + refDebito );
                            }

                            mensaje = "Transaccion Exitosa";
                            if(idioma.equalsIgnoreCase("_ve_es"))
                                mensaje = "Transaccion Exitosa";
                            else
                                mensaje = "Successful transaction";
                        } else {
                            if(idioma.equalsIgnoreCase("_ve_es"))
                                mensaje = "Transaccion Fallida";
                            else
                                mensaje = "Transaction Failed";
                            code = "1";
                        }
                    }else {
                        code = "1";
                        if(idioma.equalsIgnoreCase("_ve_es"))
                            mensaje = "Transaccion Fallida";
                        else
                            mensaje = "Transaction Failed";
                    }
                }else{
                    if(idioma.equalsIgnoreCase("_ve_es"))
                       respuesta= ResourceBundle.getBundle("vbtonline_ve_es").getString(resultadosParametros.get(1));
                    else
                       respuesta= ResourceBundle.getBundle("vbtonline_us_en").getString(resultadosParametros.get(1));
                    code = "1";

                }
            }else{
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "La cuenta a debitar que selecciono no le pertenece, por favor seleccione otra cuenta";
                else
                    mensaje = "The account you selected does not belong, please select another account";
                code = "1";
            }



//            code = exitoso.toString();
            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            if(idioma.equalsIgnoreCase("_ve_es")){
                mensaje = "Transaccion Fallida";
                respuesta = "Transaccion Fallida";
            } else   {
                mensaje = "Transaction Failed";
                respuesta = "Transaction Failed";
            }
            code = "1";
        }
        return SUCCESS;
    }

    public String saveBetweenMyAccounts() throws Exception {
        final String origen = "transfersAccion.saveBetweenMyAccounts";
        long time;
        SummaryTransfersToOtherBanksOd transfers;
        String carteras;
        String respuesta2 = new String();
        Integer exitoso;
        VBTUsersOd usuario = new VBTUsersOd();
        SelectOd cuentas2, Paises2, codBankBen2 = new SelectOd();
        String refDebito = new String();
        String refCredito = new String();
//        SelectOd cuentas= new SelectOd();

        String strNumeroCuentaCredito  = new String();
        String strCodigoCarteraCredito = new String();
        ParametrosPersonalesOd parametrosPersonales= new ParametrosPersonalesOd();
        ParametrosPersonalesOd parametrosPersonalesBase= new ParametrosPersonalesOd();
        validacionUtil validar = new validacionUtil();
        List<String> resultadosParametros;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            parametrosPersonales = (ParametrosPersonalesOd) session.get("parametrosPersonales");
            parametrosPersonalesBase = (ParametrosPersonalesOd) session.get("parametrosPersonalesBase");

            if (parametrosPersonales == null){
                parametrosPersonales = parametrosPersonalesBase;
            }


            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
//            ctaOpcOd= getBackOfficeServicio().opcionesGrupo("", );

            transfers = data.getResumenTOBs().get(0);
            resumenTOB = transfers;
            resumenTOB.setMoneda((String) session.get("moneda"));

            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            resumenTOB.setMonto_aux(resumenTOB.getAmount());
            String monto2 = resumenTOB.getAmount().replace(".","");
            resumenTOB.setAmount(monto2.replace(",","."));

            carteras = (String) session.get("carteras");
            List<ContentSelectOd> cuentas = (List<ContentSelectOd> ) session.get("cuentasTOB");
            String strNumeroCuentaDebito  = resumenTOB.getAccountCode().substring(0,resumenTOB.getAccountCode().indexOf("|")).trim();
            String strCodigoCarteraDebito = resumenTOB.getAccountCode().substring((resumenTOB.getAccountCode().indexOf("|")+1),resumenTOB.getAccountCode().length()).trim();
            //valido la cuenta a debitar
            if(validar.validarCuentaTransfer(carteras, cuentas, strNumeroCuentaDebito, strCodigoCarteraDebito).equalsIgnoreCase("SI")){
                strNumeroCuentaCredito  = resumenTOB.getBeneficiaryAccount().substring(0, resumenTOB.getBeneficiaryAccount().indexOf("|")).trim();
                strCodigoCarteraCredito = resumenTOB.getBeneficiaryAccount().substring((resumenTOB.getBeneficiaryAccount().indexOf("|")+1),resumenTOB.getBeneficiaryAccount().length()).trim();
                //valido la cuenta a acreditar
                if(validar.validarCuentaTransfer(carteras, cuentas, strNumeroCuentaCredito, strCodigoCarteraCredito).equalsIgnoreCase("SI")){
                    //Valida Los Parametros Personales (Solo valida todos Menos FC)
                    if ((usuario.getTipo().equalsIgnoreCase("6"))||(usuario.getTipo().equalsIgnoreCase("7"))||(usuario.getTipo().equalsIgnoreCase("8"))){
                        resultadosParametros= new ArrayList<>();
                        resultadosParametros.add("OK");
                    }else{
                        resultadosParametros=getTransfersServicio().validarParametrosPersonales(parametrosPersonales.getCodpercli(),resumenTOB.getAmount(),"TIN",parametrosPersonales.getNum_contrato(),"C");
                    }
                    if(resultadosParametros.get(0).equalsIgnoreCase("OK")){
                        respuesta2 = getTransfersServicio().saveBetweenMyAccounts(resumenTOB, usuario, idioma);
                        if (respuesta2 != null) {
                            refDebito = respuesta2.substring((respuesta2.indexOf("|") + 1), respuesta2.indexOf("!")).trim();
                            refCredito = respuesta2.substring((respuesta2.indexOf("!") + 1), respuesta2.length()).trim();
                            respuesta2 = respuesta2.substring(0, (respuesta2.indexOf("|"))).trim();
                            numref = refDebito;
                            if (respuesta2.equalsIgnoreCase("OK")) {
                                code = "0";

                                if ((usuario.getTipo().equalsIgnoreCase("6"))||(usuario.getTipo().equalsIgnoreCase("7"))||(usuario.getTipo().equalsIgnoreCase("8"))){
                                    this.GuardarLogFC(usuario.getLogin(),"19","1","4",strNumeroCuentaDebito,usuario.getIP(), "Transferencia entre mis Cuentas N° ref: " + refDebito ,usuario.getNumContrato());
                                }else{
                                    this.GuardarLog(usuario.getLogin(),"16","1","4",strNumeroCuentaDebito,usuario.getIP(), "N° ref: " + refDebito );
                                }


                                if(idioma.equalsIgnoreCase("_ve_es"))
                                    mensaje = "Transaccion Exitosa";
                                else
                                    mensaje = "Successful transaction";
                            } else {
                                if(idioma.equalsIgnoreCase("_ve_es"))
                                    mensaje = "Transaccion Fallida";
                                else
                                    mensaje = "Transaction Failed";
                                code = "1";
                            }
                        } else {
                            code = "1";
                            if(idioma.equalsIgnoreCase("_ve_es"))
                                mensaje = "Transaccion Fallida";
                            else
                                mensaje = "Transaction Failed";
                        }
                    }else{
                        if(idioma.equalsIgnoreCase("_ve_es"))
                            respuesta= ResourceBundle.getBundle("vbtonline_ve_es").getString(resultadosParametros.get(1));
                        else
                            respuesta= ResourceBundle.getBundle("vbtonline_us_en").getString(resultadosParametros.get(1));
                        code = "1";
                    }
                }else{
                    if(idioma.equalsIgnoreCase("_ve_es"))
                        mensaje = "La cuenta a debitar que selecciono no le pertenece, por favor seleccione otra cuenta";
                    else
                        mensaje = "The account you selected does not belong, please select another account";
                    code = "1";
                }

            }else{
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "La cuenta a debitar que selecciono no le pertenece, por favor seleccione otra cuenta";
                else
                    mensaje = "The account you selected does not belong, please select another account";
                code = "1";
            }


//            code = exitoso.toString();
            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            if(idioma.equalsIgnoreCase("_ve_es")){
                mensaje = "Transaccion Fallida";
                respuesta = "Transaccion Fallida";
            } else {
                mensaje = "Transaction Failed";
                respuesta = "Transaction Failed";
            }
            code = "1";
        }
        return SUCCESS;
    }
    //No se usa
    public String saveToOtherBanks_JS() throws Exception {
        final String origen = "transfersAccion.saveToOtherBanks";
        long time;
        SummaryTransfersToOtherBanksOd transfers;
        String carteras;
        String respuesta = new String();
        List<String> resultados = new ArrayList<>();
        Integer exitoso;
        VBTUsersOd usuario = new VBTUsersOd();
        SelectOd cuentas2, Paises2, codBankBen2 = new SelectOd();
//        SelectOd cuentas= new SelectOd();
        String strEmailsInternos = new String();
        MailManager mailManager = new MailManager("vbtonline");
        ParametrosPersonalesOd parametrosPersonalesFC = new ParametrosPersonalesOd();
        ParametrosPersonalesOd parametrosPersonalesBaseFC = new ParametrosPersonalesOd();
        validacionUtil validar = new validacionUtil();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            parametrosPersonalesFC = (ParametrosPersonalesOd) session.get("parametrosPersonales");
            parametrosPersonalesBaseFC = (ParametrosPersonalesOd) session.get("parametrosPersonalesBase");

            if (parametrosPersonalesFC == null){
                parametrosPersonalesFC = parametrosPersonalesBaseFC;
            }

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
//            ctaOpcOd= getBackOfficeServicio().opcionesGrupo("", );

            transfers = data.getResumenTOBs().get(0);
            resumenTOB = transfers;
            resumenTOB.setMoneda((String) session.get("moneda"));

            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            resumenTOB.setMonto_aux(resumenTOB.getAmount());
            String monto = resumenTOB.getAmount().replace(".", "");
            resumenTOB.setAmount(monto.replace(",", "."));

            carteras = (String) session.get("carteras");
            List<ContentSelectOd> cuentas = (List<ContentSelectOd> ) session.get("cuentasTOB");
            String strNumeroCuentaDebito  = resumenTOB.getAccountCode().substring(0,resumenTOB.getAccountCode().indexOf("|")).trim();
            String strCodigoCarteraDebito = resumenTOB.getAccountCode().substring((resumenTOB.getAccountCode().indexOf("|")+1),resumenTOB.getAccountCode().length()).trim();

            if(validar.validarCuentaTransfer(carteras, cuentas, strNumeroCuentaDebito, strCodigoCarteraDebito).equalsIgnoreCase("SI")){
                if(validar.validarMontosTransferenciaFC(parametrosPersonalesFC, resumenTOB, "TOB").equalsIgnoreCase("OK")){
//                    code = "0";
                    resultados = getTransfersServicio().saveToOtherBank_JS(resumenTOB, usuario, idioma);
                    if (resultados.get(0).equalsIgnoreCase("OK")) {


                        strNumeroCuentaDebito = resumenTOB.getAccountCode().substring(0, resumenTOB.getAccountCode().indexOf("|")).trim();
                        strEmailsInternos = getTransfersServicio().EmailsInternos(strNumeroCuentaDebito);
                        this.GuardarLog(usuario.getLogin(),"16","1","4",strNumeroCuentaDebito,usuario.getIP(), "N° ref: " + resultados.get(1) );
                        mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("Transferencias" + idioma).getString("TAGMsgTituloEmailCliente"), resultados.get(2));
                        if (!NullFormatter.isBlank(strEmailsInternos)) {
                            mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), strEmailsInternos, ResourceBundle.getBundle("Transferencias" + idioma).getString("TAGMsgEmailNotificacionTransferenciaExterna"), resultados.get(3));
                        }

                        if (!NullFormatter.isBlank(ResourceBundle.getBundle("vbtonline").getString("mail_transferencias"))) {
                            mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_transferencias"), ResourceBundle.getBundle("Transferencias" + idioma).getString("TAGMsgEmailNotificacionTransferenciaExterna"), resultados.get(3));
                        }

                        code = "0";

                        if(idioma.equalsIgnoreCase("_ve_es"))
                            mensaje = "Transaccion exitosa";
                        else
                            mensaje = "Successful transaction";
                        numref = resultados.get(1);

                        if ("FW".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                            resumenTOB.setIntermediaryBankCodeType("ABA");
                        } else if ("SA".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                            resumenTOB.setIntermediaryBankCodeType("SWIFT");
                        } else if ("CHIPS".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                            resumenTOB.setIntermediaryBankCodeType("CHIPS");
                        } else if ("".equalsIgnoreCase(resumenTOB.getIntermediaryBankCodeType())) {
                            resumenTOB.setIntermediaryBankCodeType("ACCOUNT");
                        }

                        if ("FW".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                            resumenTOB.setBeneficiaryBankCodeType("ABA");
                        } else if ("SA".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                            resumenTOB.setBeneficiaryBankCodeType("SWIFT");
                        } else if ("CHIPS".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                            resumenTOB.setBeneficiaryBankCodeType("CHIPS");
                        } else if ("".equalsIgnoreCase(resumenTOB.getBeneficiaryBankCodeType())) {
                            resumenTOB.setBeneficiaryBankCodeType("ACCOUNT");
                        }
                    } else {

                        if(idioma.equalsIgnoreCase("_ve_es"))
                            mensaje = "Fallo durante el registro de la transferencia";
                        else
                            mensaje = "Failure during the transfer register";
                        code = "1";
                    }
                }else{
                    if(idioma.equalsIgnoreCase("_ve_es"))
                        mensaje = "El monto de la transferencia excede los indicados por sus parametros personales";
                    else
                        mensaje = "The transfer amount exceeds the amounts indicated by your \"personal parameters\"";
                    code = "1";
                }
            }else{
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "La cuenta a debitar que selecciono no le pertenece, por favor seleccione otra cuenta";
                else
                    mensaje = "The account you selected does not belong, please select another account";
                code = "1";
            }


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Transaccion Fallida";
            else
                mensaje = "Transaction Failed";
            code = "1";
        }
        return SUCCESS;
    }

    public String consultarTransferencias() throws Exception {
        final String origen = "transfersAccion.consultarTransferencias";
        long time;
        SummaryTransfersToOtherBanksOd transfers;
        String carteras;
        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);

            List<String> parametros = new ArrayList<>();
            carteras = (String) session.get("carteras");
            usuario = (VBTUsersOd) session.get("user");
            parametros.add(carteras);
            parametros.add(usuario.getNumContrato());
            if(data.getParametros().get(0).equalsIgnoreCase(""))
              parametros.add(null);
            else
              parametros.add(data.getParametros().get(0));

            if(data.getParametros().get(1).equalsIgnoreCase(""))
              parametros.add(null);
            else
              parametros.add(data.getParametros().get(1));

            parametros.add(usuario.getTipo());

            idioma = (String) session.get("idioma");
            TableOd tabla = getTransfersServicio().consultarTransferenciasGeneral(parametros, idioma);
            contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tabla.getContenidoTabla_info();

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);
            getTransfersServicio().guardarLog(usuario.getLogin(),"3","1","14","",usuario.getIP(), "consultar Historico de Transferencia");


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String validarClaveTransferenciasTOB() throws Exception {
        final String origen = "transfersAccion.validarClaveTransferenciasTOB";
        long time;
        Integer intentos_clave = 0;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            idioma = (String) session.get("idioma");
            intentos_clave = (Integer) session.get("intentos_clave");
            String claveGenerada = (String) session.get("clave_generada");
            if(intentos_clave<3){
                if(data.getParametros().get(0).equalsIgnoreCase(claveGenerada)){
                    claveValida ="OK";
                    mensaje="OK";
                    intentos_clave = 0;
                    session.put("intentos_clave",intentos_clave);
                    session.remove("clave_generada");
                }else{
                    if(intentos_clave==2){
                        claveValida ="Cancel";
                        if(idioma.equalsIgnoreCase("_us_en")) {
                            mensaje ="The Transaction Key you entered is wrong for third time, your operation was canceled";
                        }else{
                            mensaje ="Clave de confirmaci\u00f3n de operaci\u00f3n incorrecta. <br> Si se equivoca tres veces colocando su clave de operaciones, Esta ser&aacute; cancelada";
                        }
                    }else{
                        claveValida ="NO OK";
                            if(idioma.equalsIgnoreCase("_us_en")) {
                                mensaje ="The Transaction Key you entered is wrong <br> If you failed three times entering your Transaction Key, this operation will be canceled";
                            }else{
                                mensaje ="Clave de confirmaci\u00f3n de operaci\u00f3n incorrecta. <br> Si se equivoca tres veces colocando su clave de operaciones, Esta ser&aacute; cancelada";
                            }
                        intentos_clave++;
                        session.put("intentos_clave",intentos_clave);
                    }

                }
            }

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String validarClaveTransferenciasAprobar() throws Exception {
        final String origen = "transfersAccion.validarClaveTransferenciasAprobar";
        long time;
        Integer intentos_clave = 0;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            idioma = (String) session.get("idioma");
            intentos_clave = (Integer) session.get("intentos_clave");
            String claveGenerada = (String) session.get("clave_generada");
            numReferencia = (String) session.get("numReferencia");
            if(intentos_clave<3){
                if(data.getParametros().get(0).equalsIgnoreCase(claveGenerada)){
                    claveValida ="OK";
                    intentos_clave = 0;
                    session.put("intentos_clave",intentos_clave);
                    session.remove("clave_generada");
                }else{
                    if(intentos_clave==2){
                        claveValida ="Cancel";
                    }else{
                        claveValida ="NO OK";
                        intentos_clave++;
                        session.put("intentos_clave",intentos_clave);
                    }

                }
            }





            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public String cargarBuscarBanco() throws Exception {
        final String origen = "transfersAccion.cargarBuscarBanco";
        long time;
        SelectOd  Paises2, Paises3, codBankBen2= new SelectOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            idioma= (String) session.get("idioma");
            if (session.get("Paises") == null) {
                Paises2 = getTransfersServicio().cargarPaises();
                Paises = Paises2.getContenido();
                session.put("Paises", Paises);

            } else {
                Paises = (List<ContentSelectOd>) session.get("Paises");
            }

            if (session.get("codBankBen") == null) {
                codBankBen2 = getTransfersServicio().cargarCodBankBen();
                codBankBen = codBankBen2.getContenido();
                session.put("codBankBen", codBankBen);
            } else {
                codBankBen = (List<ContentSelectOd>) session.get("codBankBen");
            }

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String buscarBanco() throws Exception {
        final String origen = "transfersAccion.buscarBanco";
        long time;
        TableOd bancos = new TableOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);

            idioma= (String) session.get("idioma");
            List<String> parametros = new ArrayList<>();
//            p_strCmbTipoCodBanco
            if(data.getParametros().get(0).equalsIgnoreCase(""))
                parametros.add(null);
            else
                parametros.add(data.getParametros().get(0));
            //p_strTxtCodBanco
            if(data.getParametros().get(1).equalsIgnoreCase(""))
                parametros.add(null);
            else
                parametros.add(data.getParametros().get(1));
            //p_strTxtNombreBanco
            if(data.getParametros().get(2).equalsIgnoreCase(""))
                parametros.add(null);
            else
                parametros.add(data.getParametros().get(2));
            //p_strCmbPais
            if(data.getParametros().get(3).equalsIgnoreCase(""))
                parametros.add(null);
            else
                parametros.add(data.getParametros().get(3));
            //p_strCodPais
            if(data.getParametros().get(4).equalsIgnoreCase(""))
                parametros.add(null);
            else
                parametros.add(data.getParametros().get(4));

            banco = data.getParametros().get(5);

            bancos = getTransfersServicio().buscarBanco(parametros,idioma);
//
            contenidoTabla_culumnasTest = bancos.getContenidoTabla_culumnas();
            contenidoTabla_infoTest = bancos.getContenidoTabla_info();

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TransfersAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "transfersAccion.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+TransfersAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getTransfersServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+TransfersAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return respuesta;
    }


    public String GuardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7, String parametro8) throws Exception {
        final String origen = "transfersAccion.GuardarLogFC";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+TransfersAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getTransfersServicio().guardarLogFC(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7,parametro8);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+TransfersAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return respuesta;
    }



    public String aceptarCondicionesTransfe() throws Exception {
        final String origen = "transfersAccion.aceptarCondicionesTransfe";
        long time;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+TransfersAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            //String acepto=code;
            String acepto="S";
            VBTUsersOd usuario=(VBTUsersOd)session.get("user");
            usuario.setTerminosCondiciones( getTransfersServicio().aceptarCondicionesTransfe(usuario.getNumContrato(), usuario.getLogin(), usuario.getIP(), usuario.getLast_login(),acepto));
            session.put("user",usuario) ;
            time = System.currentTimeMillis () - time;
            if (acepto.equalsIgnoreCase("S"))
                this.GuardarLog(usuario.getLogin(),"4","1","7",usuario.getNumContrato(),usuario.getIP(), "Acepto los terminos y condiciones para las transferencias Contrato Nº: "+ usuario.getNumContrato());
            else
                this.GuardarLog(usuario.getLogin(),"4","1","7",usuario.getNumContrato(),usuario.getIP(), "No Acepto los terminos y condiciones para las transferencias Contrato Nº: "+ usuario.getNumContrato());
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+TransfersAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return SUCCESS;
    }






    public TransfersOd getTransfers() {
        return transfers;
    }


    public void setTransfers(TransfersOd transfers) {
        this.transfers = transfers;
    }

    public ITransfersServicio getTransfersServicio() {
        return transfersServicio;
    }

    public void setTransfersServicio(ITransfersServicio transfersServicio) {
        this.transfersServicio = transfersServicio;
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

    public String getJsonTransfers() {
        return jsonTransfers;
    }

    public void setJsonTransfers(String jsonTransfers) {
        this.jsonTransfers = jsonTransfers;
    }

    public List<ContentSelectOd> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<ContentSelectOd> cuentas) {
        this.cuentas = cuentas;
    }

    public List<ContentSelectOd> getPaises() {
        return Paises;
    }

    public void setPaises(List<ContentSelectOd> Paises) {
        this.Paises = Paises;
    }

    public List<ContentSelectOd> getCodBankBen() {
        return codBankBen;
    }

    public void setCodBankBen(List<ContentSelectOd> codBankBen) {
        this.codBankBen = codBankBen;
    }

    public SummaryTransfersToOtherBanksOd getResumenTOB() {
        return resumenTOB;
    }

    public void setResumenTOB(SummaryTransfersToOtherBanksOd resumenTOB) {
        this.resumenTOB = resumenTOB;
    }

    public Integer getExisteBen() {
        return existeBen;
    }

    public void setExisteBen(Integer existeBen) {
        this.existeBen = existeBen;
    }

    public Integer getExisteInt() {
        return existeInt;
    }

    public void setExisteInt(Integer existeInt) {
        this.existeInt = existeInt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ContentSelectOd> getCuentasTOB() {
        return cuentasTOB;
    }

    public void setCuentasTOB(List<ContentSelectOd> cuentasTOB) {
        this.cuentasTOB = cuentasTOB;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getJsonTransfers_ValidarCuenta_TOC() {
        return jsonTransfers_ValidarCuenta_TOC;
    }

    public void setJsonTransfers_ValidarCuenta_TOC(String jsonTransfers_ValidarCuenta_TOC) {
        this.jsonTransfers_ValidarCuenta_TOC = jsonTransfers_ValidarCuenta_TOC;
    }

    public String getMonedaTOC() {
        return monedaTOC;
    }

    public void setMonedaTOC(String monedaTOC) {
        this.monedaTOC = monedaTOC;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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

    public SummaryTransfersToOtherBanksOd getDatos() {
        return datos;
    }

    public void setDatos(SummaryTransfersToOtherBanksOd datos) {
        this.datos = datos;
    }

    public List<ContentTemplateOd> getListaTemplates() {
        return listaTemplates;
    }

    public void setListaTemplates(List<ContentTemplateOd> listaTemplates) {
        this.listaTemplates = listaTemplates;
    }

    public String getTAGMsgInfoNombreBeneficiario() {
        return TAGMsgInfoNombreBeneficiario;
    }

    public void setTAGMsgInfoNombreBeneficiario(String TAGMsgInfoNombreBeneficiario) {
        this.TAGMsgInfoNombreBeneficiario = TAGMsgInfoNombreBeneficiario;
    }

    public String getClaveValida() {
        return claveValida;
    }

    public void setClaveValida(String claveValida) {
        this.claveValida = claveValida;
    }

    public String getNumref() {
        return numref;
    }

    public void setNumref(String numref) {
        this.numref = numref;
    }

    public List<String> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<String> monedas) {
        this.monedas = monedas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public IDesingBankServicio getDesingBankServicio() {
        return desingBankServicio;
    }

    public void setDesingBankServicio(IDesingBankServicio desingBankServicio) {
        this.desingBankServicio = desingBankServicio;
    }

    public String getEncontro() {
        return encontro;
    }

    public void setEncontro(String encontro) {
        this.encontro = encontro;
    }

    public String getNumReferencia() {
        return numReferencia;
    }

    public void setNumReferencia(String numReferencia) {
        this.numReferencia = numReferencia;
    }

    public List<String> getDetalle_transferencia() {

        return detalle_transferencia;
    }

    public List<ContentSelectOd> getPaisesBeneficiario() {
        return PaisesBeneficiario;
    }

    public void setPaisesBeneficiario(List<ContentSelectOd> paisesBeneficiario) {
        PaisesBeneficiario = paisesBeneficiario;
    }

    public void setDetalle_transferencia(List<String> detalle_transferencia) {
        this.detalle_transferencia = detalle_transferencia;
    }

    public String getAccountSelected() {
        return accountSelected;
    }

    public void setAccountSelected(String accountSelected) {
        this.accountSelected = accountSelected;
    }

    public VBTUsersOd getUsuario() {
        return usuario;
    }
    public void setUsuario(VBTUsersOd usuario) {
        this.usuario = usuario;
    }

    public String getMontoInternas() {
        return montoInternas;
    }

    public void setMontoInternas(String montoInternas) {
        this.montoInternas = montoInternas;
    }

    public String getMontoExternas() {
        return montoExternas;
    }

    public void setMontoExternas(String montoExternas) {
        this.montoExternas = montoExternas;
    }

    public String getCantidadInternas() {
        return cantidadInternas;
    }

    public void setCantidadInternas(String cantidadInternas) {
        this.cantidadInternas = cantidadInternas;
    }

    public String getCantidadExternas() {
        return cantidadExternas;
    }

    public void setCantidadExternas(String cantidadExternas) {
        this.cantidadExternas = cantidadExternas;
    }
}






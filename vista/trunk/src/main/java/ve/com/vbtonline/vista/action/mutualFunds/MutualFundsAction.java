package ve.com.vbtonline.vista.action.mutualFunds;

import com.google.gson.Gson;
import java.io.InputStream;
import com.opensymphony.xwork2.ActionSupport;
import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.mutualFunds.IMutualFundsServicio;
import ve.com.vbtonline.servicio.negocio.reportsPdf.IReportsPdfServicio;

import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.TransformTable;
import ve.com.vbtonline.servicio.util.validacionUtil;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.codec.binary.Base64.decodeBase64;


/**
 * Created by IntelliJ IDEA.
 * User: Rafael Godoy
 * Date: 13/07/2012
 * Time: 05:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class MutualFundsAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(MutualFundsAction.class);
    private FabricaTo fabrica;

    private MutualFundsOd mutualFunds;
    private IMutualFundsServicio mutualFundsServicio;

    private IReportsPdfServicio reportsPDFServicio;

    private String mensaje;
    private String codigo;
    private DataJson data=new DataJson();
    private String jsonMutualFunds;
    private String jsonMutualFundsTabla;
    private String jsonDetalleEdoCuentaFM;
    private Map session;
    private List<ContentSelectOd> fondos;
    private List<ContentSelectOd> fondosEC;
    private List<ContentSelectOd> tipoTransacciones;
    private List<ContentSelectOd> rango;
    private List<String> detalle;
    private String fechaCierre;
    private EncabezadoOtrasInversionesEdoCtaOd encabezado;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasTest;
    private List<ContentTableInfoOd> contenidoTabla_infoTest;
//    private List<List<String>> listaEncabezado;
    private List<ConfiguracionEstadoCuentaFMOd> listaEncabezado;
    private String idioma;
    private String numeroCuenta;

    private List<ContentSelectOd> meses;
    private String mes;
    private String anio;
    private InputStream respuestaInputStream;

    //Parameters pdf
    private String paramCero;
    private String paramUno;
    private String paramDos;
    private String paramTres;
    private String paramCuatro;
    private String paramCinco;

    public MutualFundsAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }

    public String execute() throws Exception {
        final String origen = "mutualFundsAction.execute";
        long time;
        MutualFundsOd mutualFunds;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }

    public String cargar() throws Exception {
        final String origen = "mutualFundsAccion.cargar";
        long time;
        MutualFundsOd mutualFunds;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonMutualFunds, DataJson.class);
            mutualFunds=getMutualFundsServicio().cargar("", data.getMutualFundss().get(0), usuario);
            setMensaje(mutualFunds.getId().toString());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarFondosMutuales() throws Exception {
        final String origen = "mutualFundsAccion.cargarFondosMutuales";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        SelectOd fondos2 = new SelectOd();
        CuentasOd detalles = new CuentasOd();
        anio = "";

        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");

//            List<String> mesano = new ArrayList<String>();
//            mesano = accountsServicio.consultarMesAnoMaximo(usuario);
//            mes = mesano.get(0);
//            anio = mesano.get(1);
//            if(session.get("colocaciones")==null){
            detalles=getMutualFundsServicio().cargarFondosMutuales(carteras, idioma, usuario);

            fondos=detalles.getContenido();

            if(fondos.size()==0)
                codigo = "1";
            else
                codigo = "0";

            fechaCierre=detalles.getFecha();
            session.put("fondos",fondos);

            rango = new ArrayList<>();
            ContentSelectOd periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGUltimos5Dias"));
            periodo.setValue("5");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGUltimos15Dias"));
            periodo.setValue("15");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGUltimos30Dias"));
            periodo.setValue("30");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGUltimos90Dias"));
            periodo.setValue("90");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGRangoDeFechas"));
            periodo.setValue("-1");
            rango.add(periodo);

//            }else{
//                 colocaciones = (List<ContentSelectOd>) session.get("colocaciones");
//            }

            String language = new String();
            String locale = new String();

            if(idioma.equalsIgnoreCase("_ve_es")){
                language = "es";
                locale = "ve";
            }else{
                language = "en";
                locale = "us";
            }

            Calendar miCalendario = Calendar.getInstance();
            SimpleDateFormat sdfFormatoFecha=new SimpleDateFormat("MMMMM",new Locale(language,locale.toUpperCase()));
            List<String> strFechaTemp = new ArrayList<String>();
            miCalendario.set(miCalendario.MONTH, miCalendario.JANUARY);
            meses = new ArrayList<ContentSelectOd>();

            Integer con = 1;
            for (int i = 0; i < 12; i++) {
                campo.setLabel((sdfFormatoFecha.format(miCalendario.getTime())).toUpperCase());
                campo.setValue(con.toString());
                meses.add(campo);
                campo = new ContentSelectOd();
                miCalendario.add(miCalendario.MONTH, 1);
                con = con + 1;
            } // end for %>



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarFondosMutualesRazonMoneda() throws Exception {

        final String origen = "mutualFundsAction.cargarFondosMutualesRazonMoneda";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        SelectOd fondos2 = new SelectOd();
        CuentasOd detalles = new CuentasOd();
        anio = "";

        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");
            detalles=getMutualFundsServicio().cargarFondosMutualesRazonMoneda(carteras,idioma,usuario);

            fondos=detalles.getContenido();

            if(fondos.size()==0)
                codigo = "1";
            else
                codigo = "0";

            fechaCierre=detalles.getFecha();
            session.put("fondos",fondos);

            rango = new ArrayList<>();
            ContentSelectOd periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGUltimos5Dias"));
            periodo.setValue("5");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGUltimos15Dias"));
            periodo.setValue("15");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGUltimos30Dias"));
            periodo.setValue("30");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGUltimos90Dias"));
            periodo.setValue("90");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGRangoDeFechas"));
            periodo.setValue("-1");
            rango.add(periodo);

//            }else{
//                 colocaciones = (List<ContentSelectOd>) session.get("colocaciones");
//            }

            String language = new String();
            String locale = new String();

            if(idioma.equalsIgnoreCase("_ve_es")){
                language = "es";
                locale = "ve";
            }else{
                language = "en";
                locale = "us";
            }

            Calendar miCalendario = Calendar.getInstance();
            SimpleDateFormat sdfFormatoFecha=new SimpleDateFormat("MMMMM",new Locale(language,locale.toUpperCase()));
            List<String> strFechaTemp = new ArrayList<String>();
            miCalendario.set(miCalendario.MONTH, miCalendario.JANUARY);
            meses = new ArrayList<ContentSelectOd>();

            Integer con = 1;
            for (int i = 0; i < 12; i++) {
                campo.setLabel((sdfFormatoFecha.format(miCalendario.getTime())).toUpperCase());
                campo.setValue(con.toString());
                meses.add(campo);
                campo = new ContentSelectOd();
                miCalendario.add(miCalendario.MONTH, 1);
                con = con + 1;
            } // end for %>

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarFondosMutualesBloqueos() throws Exception {
        final String origen = "mutualFundsAccion.cargarFondosMutualesBloqueos";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        SelectOd fondos2 = new SelectOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            this.cargarFondosMutuales();

            if(fondos.size()==0)
                codigo = "1";
            else
                codigo = "0";

            idioma = (String) session.get("idioma");
            rango = new ArrayList<>();
            ContentSelectOd periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosBloqueos"+idioma).getString("TAGVigentes"));
            periodo.setValue("vigentes");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosBloqueos"+idioma).getString("TAGVencidos"));
            periodo.setValue("vencidos");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosBloqueos"+idioma).getString("TAGRangoFechas"));
            periodo.setValue("rangoFechas");
            rango.add(periodo);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String consultarDetalleFondosMutualesBT() throws Exception {
        final String origen = "mutualFundsAccion.consultarDetalleFondosMutualesBT";
        long time;
        List<String> parametros;
        String  strCodigoFondo = new String();
        String  strCodigoCartera = new String();
        SelectOd tipoTrans = new SelectOd();
        validacionUtil validar = new validacionUtil();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String carteras = (String) session.get("carteras");
            fondos = (List<ContentSelectOd>) session.get("fondos");
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonMutualFunds, DataJson.class);
            parametros = new ArrayList<String>();
            idioma = (String) session.get("idioma");
//
            strCodigoFondo  = data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|"));
            strCodigoCartera = data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|") + 1), data.getParametros().get(0).length());


            parametros.add(strCodigoCartera);
            parametros.add(strCodigoFondo);

            if(validar.validarColocaciones(carteras, fondos, data).equalsIgnoreCase("SI")){
                detalle = getMutualFundsServicio().consultarDetalleFondosMutualesBT(parametros, usuario);
                tipoTrans = getMutualFundsServicio().cargarTipoTransaccionBT(parametros,idioma, usuario);
                tipoTransacciones = tipoTrans.getContenido();
                numeroCuenta = data.getParametros().get(0);
                codigo = "0";
            }else{
                codigo = "1";
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "El número del fondo mutual ó el número de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
                else
                    mensaje = "El número del fondo mutual ó el número de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
            }



//


                    time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde";
            else
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde";
        }
        return SUCCESS;
    }

    public String consultarSaldosTransFondosMutuales() throws Exception {
        final String origen = "mutualFundsAction.consultarSaldosTransFondosMutuales";
        long time;
        List<String> parametros;
        String  strCodigoFondo = new String();
        String  strCodigoCartera = new String();
        SelectOd tipoTrans = new SelectOd();
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String carteras = (String) session.get("carteras");
            fondos = (List<ContentSelectOd>) session.get("fondos");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonMutualFundsTabla, DataJson.class);
            parametros = new ArrayList<String>();
            idioma = (String) session.get("idioma");
//
            strCodigoFondo  = data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|"));
            strCodigoCartera = data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|") + 1), data.getParametros().get(0).length());

            parametros.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGEnTransito"));
            parametros.add(strCodigoFondo);
            parametros.add(strCodigoCartera);
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));
            parametros.add(data.getParametros().get(4));
//            p_TAGEnTransito  IN VARCHAR2,
//                    p_strCodigoFondo   IN VARCHAR2,
//                    p_strCodigoCartera IN VARCHAR2,
//                    p_strCmbDiasConsulta IN VARCHAR2,
//                    p_strTxtFechaDesde  IN VARCHAR2,
//                    p_strTxtFechaHasta IN VARCHAR2,
            //***** falta el tipo de transaccion, arreglar el PL

            idioma = (String) session.get("idioma");

            if(validar.validarColocaciones(carteras, fondos, data).equalsIgnoreCase("SI")){
                TableOd tabla = getMutualFundsServicio().consultarSaldosTransFondosMutuales(parametros, idioma, usuario);
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                codigo = "0";
                this.GuardarLog(usuario.getLogin(),"3","1","6",strCodigoFondo,usuario.getIP(),"Saldos y Trans. (N° Cartera:" + strCodigoCartera + ")");
            }else{
                codigo = "1";
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "El número del fondo mutual ó el número de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
                else
                    mensaje = "El número del fondo mutual ó el número de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
            }

//


                    time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde";
            else
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde";
        }
        return SUCCESS;
    }

    public String consultarBloqueosFondosMutuales() throws Exception {
        final String origen = "mutualFundsAccion.consultarBloqueosFondosMutuales";
        long time;
        List<String> parametros;
        String  strCodigoFondo = new String();
        String  strCodigoCartera = new String();
        SelectOd tipoTrans = new SelectOd();
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String carteras = (String) session.get("carteras");
            fondos = (List<ContentSelectOd>) session.get("fondos");

            Gson gson=new Gson();
            this.
            data = gson.fromJson(this.jsonMutualFundsTabla, DataJson.class);
            parametros = new ArrayList<String>();

            strCodigoFondo  = data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|"));
            strCodigoCartera = data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|") + 1), data.getParametros().get(0).length());

            parametros.add(data.getParametros().get(1));
            parametros.add(strCodigoFondo);
            parametros.add(strCodigoCartera);
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));

            idioma = (String) session.get("idioma");

            if(validar.validarColocaciones(carteras, fondos, data).equalsIgnoreCase("SI")){
                TableOd tabla = getMutualFundsServicio().consultarBloqueosFondosMutuales(parametros, idioma, usuario);
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                codigo = "0";
               this.GuardarLog(usuario.getLogin(),"3","1","6",strCodigoFondo,usuario.getIP(),"Bloqueos (N° Cartera:" + strCodigoCartera + ")");
            }else{
                codigo = "1";
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "El número del fondo mutual ó el número de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
                else
                    mensaje = "El número del fondo mutual ó el número de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde";
            else
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde";
        }
        return SUCCESS;
    }

    public String consultarEstadoCuenta() throws Exception {

        final String origen = "mutualFundsAction.consultarEstadoCuenta";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        TableOd listaMovimientos;
        int nroDecimales = 0;

        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String carteras = (String) session.get("carteras");
            List<ContentSelectOd> cuentas = (List<ContentSelectOd> ) session.get("cuentasUsuario");
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            parametros = new ArrayList<String>();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonDetalleEdoCuentaFM, DataJson.class);

            EstadoCuentaFMGeneralOd resp = null;

            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));
            parametros.add(data.getParametros().get(4));
            parametros.add(data.getParametros().get(5));

            if (data.getParametros().get(5).equals("8")){
                nroDecimales = 8;
            }else
                nroDecimales = 6;

            listaEncabezado = getMutualFundsServicio().consultarConfiguracionEDO(parametros,usuario);

            resp=getMutualFundsServicio().consultarTablaEdoCuentaFondos(parametros,usuario);

                if (resp.getEncabezado()!=null){
                EstadoCuentaFMOd encab = resp.getEncabezado();
                double SaldoT = 0;
                SaldoT = Double.parseDouble(encab.getSaldo_unit_ant());
                encabezado = new EncabezadoOtrasInversionesEdoCtaOd();
                encabezado.setCliente(encab.getInversionista());
                encabezado.setMoneda(encab.getMoneda());

                encabezado.setUnidadesGarantia(CurrencyFormatter.formatNumber(encab.getNumaccgar(),nroDecimales,","));
                encabezado.setUnidadesDisponibles(CurrencyFormatter.formatNumber(encab.getNumaccdisp(),nroDecimales,","));

                Float suma = Float.parseFloat(encab.getNumaccgar())+Float.parseFloat(encab.getNumaccdisp());
                Float mult = suma * Float.parseFloat(encab.getPrecio_vui());

                encabezado.setVUI(CurrencyFormatter.formatNumber(encab.getPrecio_vui(),6,","));
                encabezado.setUnidadesTotales(CurrencyFormatter.formatNumber(suma.toString(), nroDecimales, ","));
                encabezado.setTotalMoneda(CurrencyFormatter.formatNumber(mult.toString(), 2, ","));

                List<List<String>> bodys = new ArrayList();
                List<String> body;
                    List<EstadoCuentaFMOd> cuerpo = resp.getDetalle();
                    EstadoCuentaFMOd detalles;


                    body=new ArrayList<String>();
                    body.add("");
                    body.add("");

                    if(idioma.equalsIgnoreCase("_ve_es")){
                        body.add("Saldo Anterior");
                    }else{
                        body.add("Balance Brought Forward");
                    }

                    body.add("");
                    body.add("");
                    body.add("");
                    body.add("");
                    body.add(CurrencyFormatter.formatNumber(encab.getSaldo_unit_ant(),nroDecimales,","));

                    bodys.add(body);

                if (resp.getDetalle()!=null){



                    for (int a=0;a<cuerpo.size();a++){
                        body=new ArrayList<String>();
                        detalles = new EstadoCuentaFMOd();
                        detalles = cuerpo.get(a);

                        body.add(NullFormatter.formatBlank(detalles.getFechope()));
                        body.add(NullFormatter.formatBlank(detalles.getFechval()));
                        body.add(NullFormatter.formatBlank(detalles.getNombtipoing()));
                        body.add(detalles.getMtoinv());
                        body.add(detalles.getPrecuni());

                        if (Double.parseDouble(detalles.getNumuni()) < 0 ){
                            body.add("");
                            body.add( NullFormatter.formatBlank (CurrencyFormatter.formatNumber(detalles.getNumuni(), nroDecimales, ",") ));
                        }else{
                            body.add( NullFormatter.formatBlank (CurrencyFormatter.formatNumber(detalles.getNumuni(), nroDecimales, ",")));
                            body.add("");
                        }

                        //SaldoT = SaldoT + Float.parseFloat(detalles.getNumuni());

                        SaldoT = SaldoT + Double.parseDouble(detalles.getNumuni());



                        body.add(CurrencyFormatter.formatNumber(SaldoT, nroDecimales, ","));
                        bodys.add(body);
                    }
                }
                List<String> header=new ArrayList<String>();
                header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGECFechaOperacion"));
                header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGECFechaValor"));
                header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGECDescripcion"));
                header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGECMonto"));
                header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGECValorUnidad"));

                String nombre_columna = "";

                for (int y=0;y<listaEncabezado.size();y++){
                    if ( (listaEncabezado.get(y).getEtiqueta().equals("ASFunds_TAGdetailNumber1")) ||
                         (listaEncabezado.get(y).getEtiqueta().equals("ASFunds_TAGdetailNumber2")) ||
                          (listaEncabezado.get(y).getEtiqueta().equals("ASFunds_TAGdetailNumber3"))){

                        switch (listaEncabezado.get(y).getEtiqueta()){
                            case "ASFunds_TAGdetailNumber1":  if(idioma.equalsIgnoreCase("_ve_es")){
                                                                    //nombre_columna += listaEncabezado.get(y).getTexto_esp()+"<br>";
                                                                }else{
                                                                    //nombre_columna += listaEncabezado.get(y).getTexto_ing()+"<br>";
                                                                }
                                                               break;
                            case "ASFunds_TAGdetailNumber2": if(idioma.equalsIgnoreCase("_ve_es")){
                                                                    //nombre_columna += listaEncabezado.get(y).getTexto_esp()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                                                                    header.add(listaEncabezado.get(y).getTexto_esp());
                                                                }else{
                                                                    //nombre_columna += listaEncabezado.get(y).getTexto_ing()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                                                                    header.add(listaEncabezado.get(y).getTexto_ing());
                                                                }
                                                               break;
                            case "ASFunds_TAGdetailNumber3": if(idioma.equalsIgnoreCase("_ve_es")){
                                                                    //nombre_columna += listaEncabezado.get(y).getTexto_esp();
                                                                    header.add(listaEncabezado.get(y).getTexto_esp());
                                                                }else{
                                                                    //nombre_columna += listaEncabezado.get(y).getTexto_ing();
                                                                    header.add(listaEncabezado.get(y).getTexto_ing());
                            }
                                                             break;
                        }
                    }
                }

              // header.add(nombre_columna);
               header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGECSaldoTotal"));
               listaMovimientos=new TableOd();
               listaMovimientos= TransformTable.getTable(header, bodys);
               contenidoTabla_culumnasTest=listaMovimientos.getContenidoTabla_culumnas();
               contenidoTabla_infoTest=listaMovimientos.getContenidoTabla_info();
               mensaje="OK";
            } else{
                mensaje="NO OK";

                    List<String> header=new ArrayList<String>();
                    header.add("");
                    header.add("");
                    header.add("");
                    header.add("");
                    header.add("");
                    header.add("");
                    header.add("");
                    header.add("");


                List<List<String>> bodys = new ArrayList();




                listaMovimientos=new TableOd();
                listaMovimientos= TransformTable.getTable(header, bodys);

                contenidoTabla_culumnasTest=listaMovimientos.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=listaMovimientos.getContenidoTabla_info();

            }

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);

        }catch(Exception e){
            logger.error(e);
            codigo = "0";
            setMensaje("Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde");
        }
        return SUCCESS;
    }

    private String space(String valor,String pos){
        if (pos.compareTo("l")==0)
            valor = "<div style='float:left'>"+ valor +"</div>";
        else if (pos.compareTo("r")==0)
            valor = "<div style='float:right'>"+ valor.substring(1)+"</div>";
        return valor;
    }

    public String generarPDFEstadoCuenta() throws Exception{

        final String origen = "mutualFundsAction.generarPDFEstadoCuenta";
        long time;

        List<String> parametros;

        try{
            parametros = new ArrayList<String>();

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);
            time = System.currentTimeMillis ();
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");

            StringUtils.newStringUtf8(decodeBase64(paramCero.getBytes()));

            parametros.add(StringUtils.newStringUtf8(decodeBase64(paramCero.getBytes())));
            parametros.add(StringUtils.newStringUtf8(decodeBase64(paramUno.getBytes())));
            parametros.add(StringUtils.newStringUtf8( decodeBase64( paramDos.getBytes())));
            parametros.add(StringUtils.newStringUtf8( decodeBase64( paramTres.getBytes())));
            parametros.add(StringUtils.newStringUtf8( decodeBase64( paramCuatro.getBytes())));
            parametros.add(StringUtils.newStringUtf8( decodeBase64( paramCinco.getBytes())));

            respuestaInputStream=getMutualFundsServicio().generarPDFEdoCuenta(parametros,usuario);
        }catch(Exception e){
            logger.error(e);
            codigo = "0";
            setMensaje("Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde");
        }
        return SUCCESS;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "mutualFundsAccion.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getMutualFundsServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return respuesta;
    }

    public IMutualFundsServicio getMutualFundsServicio() {
        return mutualFundsServicio;
    }

    public void setMutualFundsServicio(IMutualFundsServicio mutualFundsServicio) {
        this.mutualFundsServicio = mutualFundsServicio;
    }

    public Map getSession() {
        return session;
    }

    public MutualFundsOd getMutualFunds() {
        return mutualFunds;
    }

    public void setMutualFunds(MutualFundsOd mutualFunds) {
        this.mutualFunds = mutualFunds;
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

    public String getJsonMutualFunds() {
        return jsonMutualFunds;
    }

    public void setJsonMutualFunds(String jsonMutualFunds) {
        this.jsonMutualFunds = jsonMutualFunds;
    }

    public List<ContentSelectOd> getFondos() {
        return fondos;
    }

    public void setFondos(List<ContentSelectOd> fondos) {
        this.fondos = fondos;
    }

    public List<String> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<String> detalle) {
        this.detalle = detalle;
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

    public List<ContentSelectOd> getTipoTransacciones() {
        return tipoTransacciones;
    }

    public void setTipoTransacciones(List<ContentSelectOd> tipoTransacciones) {
        this.tipoTransacciones = tipoTransacciones;
    }

    public List<ContentSelectOd> getRango() {
        return rango;
    }

    public void setRango(List<ContentSelectOd> rango) {
        this.rango = rango;
    }

    public String getJsonMutualFundsTabla() {
        return jsonMutualFundsTabla;
    }

    public void setJsonMutualFundsTabla(String jsonMutualFundsTabla) {
        this.jsonMutualFundsTabla = jsonMutualFundsTabla;
    }

    public String getJsonDetalleEdoCuentaFM() {
        return jsonDetalleEdoCuentaFM;
    }

    public void setJsonDetalleEdoCuentaFM(String jsonDetalleEdoCuentaFM) {
        this.jsonDetalleEdoCuentaFM = jsonDetalleEdoCuentaFM;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
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

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public List<ContentSelectOd> getMeses() {
        return meses;
    }

    public void setMeses(List<ContentSelectOd> meses) {
        this.meses = meses;
    }
                 /*
    public List<List<String>> getListaEncabezado() {
        return this.listaEncabezado;
    }

    public void setListaEncabezado(List<List<String>> listaEncabezado) {
        this.listaEncabezado = listaEncabezado;
    }
                */

    public List<ConfiguracionEstadoCuentaFMOd> getListaEncabezado() {
        return this.listaEncabezado;
    }

    public void setListaEncabezado(List<ConfiguracionEstadoCuentaFMOd> listaEncabezado) {
        this.listaEncabezado = listaEncabezado;
    }

    public EncabezadoOtrasInversionesEdoCtaOd getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(EncabezadoOtrasInversionesEdoCtaOd encabezado) {
        this.encabezado = encabezado;
    }

    public InputStream getRespuestaInputStream() {
        return respuestaInputStream;
    }

    public void setRespuestaInputStream(InputStream respuestaInputStream) {
        this.respuestaInputStream = respuestaInputStream;
    }

    public String getParamUno() {
        return paramUno;
    }

    public void setParamUno(String paramUno) {
        this.paramUno = paramUno;
    }

    public String getParamCero() {
        return paramCero;
    }

    public void setParamCero(String paramCero) {
        this.paramCero = paramCero;
    }

    public String getParamDos() {
        return paramDos;
    }

    public void setParamDos(String paramDos) {
        this.paramDos = paramDos;
    }

    public String getParamTres() {
        return paramTres;
    }

    public void setParamTres(String paramTres) {
        this.paramTres = paramTres;
    }

    public String getParamCuatro() {
        return paramCuatro;
    }

    public void setParamCuatro(String paramCuatro) {
        this.paramCuatro = paramCuatro;
    }

    public String getParamCinco() {
        return paramCinco;
    }

    public void setParamCinco(String paramCinco) {
        this.paramCinco = paramCinco;
    }
}





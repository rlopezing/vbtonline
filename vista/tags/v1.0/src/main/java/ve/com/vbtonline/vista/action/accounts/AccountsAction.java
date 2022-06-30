package ve.com.vbtonline.vista.action.accounts;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.accounts.IAccountsServicio;
import ve.com.vbtonline.servicio.od.*;
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
public class AccountsAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(AccountsAction.class);
    private FabricaTo fabrica;
    private AccountsOd account;
    private IAccountsServicio accountsServicio;
    private String mensaje;
    private DataJson data=new DataJson();
    private String jsonAccounts;
    private Map session;
    private List<ContentSelectOd> cuentas;
    private List<ContentSelectOd> tipoTransacciones;
    private List<ContentSelectOd> TransaccionesPeriodo;
    private List<ContentSelectOd> meses;
    private List<ContentSelectOd> searchBA;
    private String fechaCierreAS;
    private DetalleCuentaEdoCtaOd detalle;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasTest;
    private List<ContentTableInfoOd> contenidoTabla_infoTest;
    private String jsonDetalleEdoCuenta;
    private String jsonDetalleSaldoTrans;
    private String mes;
    private String anio;
    private String idioma;
    private String codigo;   // 1 error 0 ok
    private String numeroCuenta;
    private String fechaCierre;





    public AccountsAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }



    public String execute() throws Exception {
        final String origen = "accountAction.execute";
        long time;
        AccountsOd account;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AccountsAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AccountsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }

    public String cargar() throws Exception {
        final String origen = "accountAccion.cargar";
        long time;
        AccountsOd account;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AccountsAction.class+" | "+origen);

            time = System.currentTimeMillis ();


            Gson gson=new Gson();
            data = gson.fromJson(this.jsonAccounts, DataJson.class);
            account=getAccountsServicio().cargar("", data.getAccountss().get(0));
            setMensaje(account.getId().toString());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AccountsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarAccountStatement() throws Exception {
        final String origen = "accountAccion.cargarAccountStatement";
        long time;
        AccountsOd account;
        String carteras= new String();
        CuentasOd cuentasDescrip = new CuentasOd();
        ContentSelectOd campo = new ContentSelectOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AccountsAction.class+" | "+origen);

            time = System.currentTimeMillis ();


//            Gson gson=new Gson();
//            data = gson.fromJson(this.jsonAccounts, DataJson.class);
            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");
            cuentasDescrip= getAccountsServicio().cargarCuentasEdoCuenta(carteras, idioma);
            cuentas=cuentasDescrip.getContenido();
            session.put("cuentasUsuario",cuentas);
            fechaCierreAS = cuentasDescrip.getFecha();

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

            searchBA = new ArrayList<ContentSelectOd>();
            campo = new ContentSelectOd();
            campo.setLabel(ResourceBundle.getBundle("CuentasBloqueos"+idioma).getString("TAGVigentes"));
            campo.setValue("vigentes");
            searchBA.add(campo);
            campo = new ContentSelectOd();
            campo.setLabel(ResourceBundle.getBundle("CuentasBloqueos"+idioma).getString("TAGVencidos"));
            campo.setValue("vencidos");
            searchBA.add(campo);
            campo = new ContentSelectOd();
            campo.setLabel(ResourceBundle.getBundle("CuentasBloqueos"+idioma).getString("TAGRangoFechas"));
            campo.setValue("rangoFechas");
            searchBA.add(campo);



            TransaccionesPeriodo = new ArrayList<ContentSelectOd>();
            List<ContentSelectOd> trans = new ArrayList<ContentSelectOd>();
            ContentSelectOd periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGUltimos5Dias"));
            periodo.setValue("5");
            trans.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGUltimos15Dias"));
            periodo.setValue("15");
            trans.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGUltimos30Dias"));
            periodo.setValue("30");
            trans.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGUltimos90Dias"));
            periodo.setValue("90");
            trans.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGRangoDeFechas"));
            periodo.setValue("-1");
            trans.add(periodo);

            setTransaccionesPeriodo(trans);
            List<String> mesano = new ArrayList<String>();
             mesano = accountsServicio.consultarMesAnoMaximo();
            mes = mesano.get(0);
            anio = mesano.get(1);
            Integer mess = Integer.parseInt(mes);
            mes = mess.toString();
            codigo ="0";

//
//

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AccountsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo ="1";
        }
        return SUCCESS;
    }

    public String consultarAccountStatement() throws Exception {
        final String origen = "accountAccion.consultarAccountStatement";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AccountsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String carteras = (String) session.get("carteras");
            List<ContentSelectOd> cuentas = (List<ContentSelectOd> ) session.get("cuentasUsuario");
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonDetalleEdoCuenta, DataJson.class);
            parametros = new ArrayList<String>();

            if(validar.validarCuenta(carteras, cuentas, data).equalsIgnoreCase("SI"))
            {
                parametros.add( data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|")).trim());
                parametros.add(data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|")+1), data.getParametros().get(0).length()).trim());
                parametros.add(data.getParametros().get(1));
                parametros.add(data.getParametros().get(2));
                detalle = new DetalleCuentaEdoCtaOd();
                detalle= getAccountsServicio().consultarDetalleCuentasEdoCuenta(parametros);
                idioma = (String) session.get("idioma");

                String saldoAnterior = new String();
                if(detalle.getSaldoAnterior()==null){
                    saldoAnterior = "0.0";
                }
                else
                    saldoAnterior = detalle.getSaldoAnterior();
                if(!saldoAnterior.contains("."))
                    saldoAnterior = saldoAnterior.replace(",",".");
                else{
                    saldoAnterior = saldoAnterior.replace(".","");
                    saldoAnterior = saldoAnterior.replace(",",".");
                }
                TableOd tabla = getAccountsServicio().consultarCuentasEdoCuenta(parametros, saldoAnterior, idioma);
                //logger.logAction(strLogin,"3","1","4",strNumeroCuenta,request.getRemoteAddr(),"Edo. Cuenta Fecha:" + strCmbMes + "/" + strTxtAño + " Cartera:" + strCodigoCartera);//
                this.GuardarLog(usuario.getLogin(),"3","1","4",data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|")).trim(),usuario.getIP(),"Edo. Cuenta Fecha:"+data.getParametros().get(1)+"/"+data.getParametros().get(2)+ " (N° Cartera:" + data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|")+1), data.getParametros().get(0).length()).trim() + ")");
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                codigo = "1";
            } else {
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                  setMensaje("El número de cuenta no coincide con los asignados a sus carteras, por favor inserte un número de cuenta valido ");
                else
                  setMensaje("The account number does not match those assigned to their portfolios, please insert a valid account number ");
                codigo = "0";
            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AccountsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            codigo = "0";
            setMensaje("Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde");
        }
        return SUCCESS;
    }

    public String consultarCuentaSaldoTrans() throws Exception {
        final String origen = "accountAccion.consultarCuentaSaldoTrans";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AccountsAction.class+" | "+origen);

            time = System.currentTimeMillis ();


            String carteras = (String) session.get("carteras");
            List<ContentSelectOd> cuentas = (List<ContentSelectOd> ) session.get("cuentasUsuario");
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonDetalleSaldoTrans, DataJson.class);
            parametros = new ArrayList<String>();


            if(validar.validarCuenta(carteras, cuentas, data).equalsIgnoreCase("SI"))
            {
                parametros.add( data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|")).trim());
                parametros.add(data.getParametros().get(1));
                parametros.add(data.getParametros().get(2));
                parametros.add(data.getParametros().get(3));
                parametros.add(data.getParametros().get(4));

                idioma = (String) session.get("idioma");
                TableOd tabla = getAccountsServicio().consultarCuentasSaldoTransf(parametros, idioma);
                //logger.logAction(strLogin,"3","1","4",strNumeroCuenta,request.getRemoteAddr(),"Saldos y Trans. (N° Cartera:" + strCodigoCartera + ")");
                this.GuardarLog(usuario.getLogin(),"3","1","4", data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|")).trim(),usuario.getIP(),"Saldos y Trans. (N° Cartera:" +  data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|")+1), data.getParametros().get(0).length()).trim() + ")");
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                codigo = "1";
            } else {
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                    setMensaje("El número de cuenta no coincide con los asignados a sus carteras, por favor inserte un número de cuenta valido ");
                else
                    setMensaje("The account number does not match those assigned to their portfolios, please insert a valid account number ");
                codigo = "0";
            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AccountsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje("Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde");
            codigo = "0";
        }
        return SUCCESS;
    }

    public String consultarCuentasBloqueos() throws Exception {
        final String origen = "accountAccion.consultarCuentasBloqueos";
        long time;
        List<String> parametros;
        fechaCierre = getAccountsServicio().consultarFechaCierre((String) session.get("carteras"));
        validacionUtil validar = new validacionUtil();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AccountsAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            String carteras = (String) session.get("carteras");
            List<ContentSelectOd> cuentas = (List<ContentSelectOd> ) session.get("cuentasUsuario");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonDetalleSaldoTrans, DataJson.class);
            parametros = new ArrayList<String>();
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            if(validar.validarCuenta(carteras, cuentas, data).equalsIgnoreCase("SI"))
            {
                parametros.add(data.getParametros().get(1));
                parametros.add( data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|")).trim());
                parametros.add(data.getParametros().get(2));
                parametros.add(data.getParametros().get(3));

                idioma = (String)  session.get("idioma");
                TableOd tabla = getAccountsServicio().consultarCuentasBloqueos(parametros, idioma);
                //            logger.logAction(strLogin,"3","1","4",strNumeroCuenta,request.getRemoteAddr(),"Bloqueos (N° Cartera:" + strCodigoCartera + ")");
                this.GuardarLog(usuario.getLogin(),"3","1","4",data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|")).trim(),usuario.getIP(),"Bloqueos (N° Cartera:" + data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|")+1), data.getParametros().get(0).length()).trim() + ")");

                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                codigo = "1";
            } else {
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                    setMensaje("El número de cuenta no coincide con los asignados a sus carteras, por favor inserte un número de cuenta valido ");
                else
                    setMensaje("The account number does not match those assigned to their portfolios, please insert a valid account number ");
                codigo = "0";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AccountsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            setMensaje("Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde");
            codigo = "0";
        }
        return SUCCESS;
    }

    public String consultarBalancesAndTransaction() throws Exception {
        final String origen = "accountAccion.consultarBalancesAndTransaction";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AccountsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonDetalleSaldoTrans, DataJson.class);
            parametros = new ArrayList<String>();
            String carteras = (String) session.get("carteras");
            List<ContentSelectOd> cuentas = (List<ContentSelectOd> ) session.get("cuentasUsuario");
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            if(validar.validarCuenta(carteras, cuentas, data).equalsIgnoreCase("SI"))
            {
                parametros.add(carteras);
                numeroCuenta = data.getParametros().get(0);
                parametros.add( data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|")).trim());
                parametros.add(data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|") + 1), data.getParametros().get(0).length()).trim());

                detalle = new DetalleCuentaEdoCtaOd();
                detalle= getAccountsServicio().consultarDetalleCuentasSaldoTrans(parametros);
                this.GuardarLog(usuario.getLogin(),"3","1","4",data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|")).trim(),usuario.getIP(),"Balances y Transacciones (N° Cartera:" + data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|")+1), data.getParametros().get(0).length()).trim() + ")");

                parametros = new ArrayList<String>();
                parametros.add( data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|")).trim());
                parametros.add(data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|") + 1), data.getParametros().get(0).length()).trim());

                tipoTransacciones = new ArrayList<ContentSelectOd>();
                tipoTransacciones = getAccountsServicio().cargarTiposMovimientosBT(parametros);
                codigo = "1";

            } else {
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                    setMensaje("El número de cuenta no coincide con los asignados a sus carteras, por favor seleccione un número de cuenta valido ");
                else
                    setMensaje("The account number does not match those assigned to their portfolios, please select a valid account number ");
                codigo = "0";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AccountsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            setMensaje("Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde");
            codigo ="0";
        }
        return SUCCESS;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "accountAccion.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AccountsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getAccountsServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AccountsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return respuesta;
    }


    public String consulta() throws Exception {
        final String origen = "accountAccion.consulta";
        long time;
        AccountsOd account;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AccountsAction.class+" | "+origen);

            time = System.currentTimeMillis ();


            account=new AccountsOd();
            TableOd tableOd=getAccountsServicio().Consulta("",account);
            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AccountsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public AccountsOd getAccount() {
        return account;
    }

    public void setAccount(AccountsOd account) {
        this.account = account;
    }

    public IAccountsServicio getAccountsServicio() {
        return accountsServicio;
    }

    public void setAccountsServicio(IAccountsServicio accountsServicio) {
        this.accountsServicio = accountsServicio;
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

    public String getJsonAccounts() {
        return jsonAccounts;
    }

    public void setJsonAccounts(String jsonAccounts) {
        this.jsonAccounts = jsonAccounts;
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

    public List<ContentSelectOd> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<ContentSelectOd> cuentas) {
        this.cuentas = cuentas;
    }

    public String getFechaCierreAS() {
        return fechaCierreAS;
    }

    public void setFechaCierreAS(String fechaCierreAS) {
        this.fechaCierreAS = fechaCierreAS;
    }

    public List<ContentSelectOd> getMeses() {
        return meses;
    }

    public void setMeses(List<ContentSelectOd> meses) {
        this.meses = meses;
    }

    public DetalleCuentaEdoCtaOd getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleCuentaEdoCtaOd detalle) {
        this.detalle = detalle;
    }

    public String getJsonDetalleEdoCuenta() {
        return jsonDetalleEdoCuenta;
    }

    public void setJsonDetalleEdoCuenta(String jsonDetalleEdoCuenta) {
        this.jsonDetalleEdoCuenta = jsonDetalleEdoCuenta;
    }

    public List<ContentSelectOd> getTipoTransacciones() {
        return tipoTransacciones;
    }

    public void setTipoTransacciones(List<ContentSelectOd> tipoTransacciones) {
        this.tipoTransacciones = tipoTransacciones;
    }

    public String getJsonDetalleSaldoTrans() {
        return jsonDetalleSaldoTrans;
    }

    public void setJsonDetalleSaldoTrans(String jsonDetalleSaldoTrans) {
        this.jsonDetalleSaldoTrans = jsonDetalleSaldoTrans;
    }

    public List<ContentSelectOd> getTransaccionesPeriodo() {
        return TransaccionesPeriodo;
    }

    public void setTransaccionesPeriodo(List<ContentSelectOd> transaccionesPeriodo) {
        TransaccionesPeriodo = transaccionesPeriodo;
    }

    public List<ContentSelectOd> getSearchBA() {
        return searchBA;
    }

    public void setSearchBA(List<ContentSelectOd> searchBA) {
        this.searchBA = searchBA;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
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

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
}





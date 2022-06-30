package ve.com.vbtonline.vista.action.desingBank;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import ve.com.vbtonline.servicio.util.MailManager365;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.io.TransfersIo;
import ve.com.vbtonline.servicio.negocio.desingBank.IDesingBankServicio;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.SimpleCrypt;
import ve.com.vbtonline.servicio.util.validacionUtil;

import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: Manuel Serrano
 * Date: 21/05/2010
 * Time: 11:33:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class DesingBankAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(DesingBankAction.class);
    private FabricaTo fabrica;
    private AccountsOd account;
    private IDesingBankServicio desingBankServicio;
    private String mensaje;
    private DataJson data=new DataJson();
    private String jsonAccounts;
    private String jsonDesingBank;
    private Map session;

    private String respuesta;
    private ParametrosPersonalesOd parametrosPersonales;
    private ParametrosPersonalesOd parametrosPersonalesBase;
    private String idioma;






    public DesingBankAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }



    public String execute() throws Exception {
        final String origen = "accountAction.execute";
        long time;
        AccountsOd account;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+DesingBankAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+DesingBankAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }


    public String cambiarClave() throws Exception {
        final String origen = "DesingBankAction.cambiarClave";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+DesingBankAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonDesingBank, DataJson.class);
            idioma = (String) session.get("idioma");


            respuesta = getDesingBankServicio().verificarClave(data.getParametros().get(1), usuario.getLogin());
            if(respuesta.equalsIgnoreCase("OK")){

                            respuesta=getDesingBankServicio().cambiarClave(data.getParametros().get(0), usuario.getLogin(), usuario);

                           this.GuardarLog(usuario.getLogin(),"8","1","1",usuario.getLogin(),usuario.getIP(),"Cambio de Clave");
                            if(idioma.equalsIgnoreCase("_ve_es"))
                              mensaje = "Su cambio de clave ha sido exitoso";
                            else
                              mensaje = "You successfully changed your password";

                            getDesingBankServicio().guardarLog(usuario.getLogin(),"1","1","8","",usuario.getIP(), "Cambio de clave exitoso  ");

            }else{
                if(idioma.equalsIgnoreCase("_ve_es"))
                   mensaje = "Ha ocurrido un error al intentar cambiar su clave. Verifique su clave actual he intente nuevamente";
                else
                   mensaje = " An error has ocurred when trying to change your Password. Verify your old password and try again.";

                getDesingBankServicio().guardarLog(usuario.getLogin(),"1","1","8","",usuario.getIP(), "Cambio de clave fallido  ");

            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+DesingBankAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String guardarParametrosPersonales() throws Exception {
        final String origen = "DesingBankAction.guardarParametrosPersonales";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+DesingBankAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonDesingBank, DataJson.class);
            parametrosPersonales = data.getParametrosPersonales().get(0);
            parametrosPersonales.setCodpercli(usuario.getCodpercli());
            parametrosPersonales.setNum_contrato(usuario.getNumContrato());

            String monto = new String();
            monto = parametrosPersonales.getVbt_mmaxtd().replace(".","");
            parametrosPersonales.setVbt_mmaxtd(monto.replace(",","."));
            monto = new String();
            monto = parametrosPersonales.getVbt_mminto().replace(".","");
            parametrosPersonales.setVbt_mminto(monto.replace(",","."));
            monto = new String();
            monto = parametrosPersonales.getVbt_mmto().replace(".","");
            parametrosPersonales.setVbt_mmto(monto.replace(",","."));
            monto = new String();
            monto = parametrosPersonales.getEx_mmtd().replace(".","");
            parametrosPersonales.setEx_mmtd(monto.replace(",","."));
            monto = new String();
            monto = parametrosPersonales.getEx_mminto().replace(".","");
            parametrosPersonales.setEx_mminto(monto.replace(",","."));
            monto = new String();
            monto = parametrosPersonales.getEx_mmto().replace(".", "");
            parametrosPersonales.setEx_mmto(monto.replace(",", "."));

            idioma = (String) session.get("idioma");
            respuesta=getDesingBankServicio().guardarParametrosPersonales(parametrosPersonales);

            if (respuesta.equalsIgnoreCase("OK")){
              if (usuario.getGrupo().equalsIgnoreCase("0000000016")){



                  getDesingBankServicio().correoClientePrincipal(usuario,idioma, parametrosPersonales);

           /*       String htmlText = "";                  //SEGUIR

                  htmlText=ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_numeroMaxTransDiarias")  +   parametrosPersonales.getVbt_mmaxtd()    +
                          "\n\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMaxTransDiarias") + ": " + parametrosPersonales.getEx_mminto()    +
                          "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMinTransOpe") + ": " +    parametrosPersonales.getVbt_mmto()    +
                          "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMaxTransOpe")+        parametrosPersonales.getVbt_mmaxtd()    +
                          "\n"+
                          "\n\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_numeroMaxTransDiarias_OB") +    parametrosPersonales.getVbt_mmaxtd()    +
                          "\n\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMaxTransDiarias_OB") +      parametrosPersonales.getVbt_mmaxtd()    +
                          "\n\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMinTransOpe_OB") +       parametrosPersonales.getVbt_mmaxtd()    +
                          "\n\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMaxTransOpe_OB")+   parametrosPersonales.getVbt_mmaxtd()    +   "\n\n\n";

                  mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),listaCorreos, ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMsgEmailTransferenciaAprobador"), htmlText);

                  getDesingBankServicio().guardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo: " + (listaCorreos + " motivado a cambio de parametros personales ").replaceAll(",", ", "));

                */
              }
            }

            time = System.currentTimeMillis () - time;

            getDesingBankServicio().guardarLog(usuario.getLogin(),"4","1","2",usuario.getCodpercli(),usuario.getIP(), "Parametros Personales ");
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+DesingBankAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            respuesta ="NO OK";
            setMensaje(respuesta);
        }
        return SUCCESS;
    }

    public String cargarParametrosPersonales() throws Exception {
        final String origen = "DesingBankAction.cargarParametrosPersonales";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+DesingBankAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            List<String> parametros = new ArrayList<String>();
            parametros.add(usuario.getCodpercli());
            parametros.add(usuario.getNumContrato());

            //if(usuario.getTipo().equals("6") || usuario.getTipo().equals("7") || usuario.getTipo().equals("8") || usuario.getTipo().equals("9")|| usuario.getGrupo().equals("0000000016")){
            if(usuario.getTipoContrato().equalsIgnoreCase("FC")){
                parametros.add("FC");
            }else{
                parametros.add("C");
            }

            parametrosPersonales = new ParametrosPersonalesOd();
            parametrosPersonales=getDesingBankServicio().cargarParametrosPersonales(parametros);

            session.put("parametrosPersonales", parametrosPersonales);

            cargarParametrosPersonalesBase();




            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+DesingBankAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarParametrosPersonalesBase() throws Exception {
        final String origen = "DesingBankAction.cargarParametrosPersonalesBase";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+DesingBankAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            String tipo="";
            //if(usuario.getTipo().equals("6") || usuario.getTipo().equals("7") || usuario.getTipo().equals("8") || usuario.getTipo().equals("9")|| usuario.getGrupo().equals("0000000016")){
            if(usuario.getTipoContrato().equalsIgnoreCase("FC")){
                tipo="FC";
            }else{
                tipo="C";
            }

            parametrosPersonalesBase = new ParametrosPersonalesOd();
            parametrosPersonalesBase=getDesingBankServicio().cargarParametrosPersonalesBase(tipo);

            session.put("parametrosPersonalesBase", parametrosPersonalesBase);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+DesingBankAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }



    public BufferedImage cargarImagenHome() throws Exception {
        final String origen = "DesingBankAction.cargarImagenHome";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        BufferedImage imagen=null;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+DesingBankAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            imagen= getDesingBankServicio().cargarImagenHome();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+DesingBankAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return imagen;
    }

    public BufferedImage cargarImagenID(String id) throws Exception {
        final String origen = "DesingBankAction.cargarImagenHome";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        BufferedImage imagen=null;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+DesingBankAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            imagen= getDesingBankServicio().cargarImagenID(id);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+DesingBankAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return imagen;
    }


    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "DesingBankAction.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+DesingBankAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getDesingBankServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+DesingBankAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return respuesta;
    }


    public String guardarLogMetodosValidacion () throws Exception {
        final String origen = "DesingBankAction.guardarLogMetodosValidacion";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+DesingBankAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            validacionUtil validar = new validacionUtil();
            data = gson.fromJson(validar.caracteresEspeciales(this.jsonDesingBank), DataJson.class);
            idioma = (String) session.get("idioma");

            respuesta= getDesingBankServicio().guardarLogFC(usuario.getLogin(), data.getParametros().get(0),  data.getParametros().get(1),  data.getParametros().get(2), "", usuario.getIP(),  data.getParametros().get(3),usuario.getNumContrato());

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+DesingBankAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return SUCCESS;
    }


    public AccountsOd getAccount() {
        return account;
    }

    public void setAccount(AccountsOd account) {
        this.account = account;
    }

    public IDesingBankServicio getDesingBankServicio() {
        return desingBankServicio;
    }

    public void setDesingBankServicio(IDesingBankServicio desingBankServicio) {
        this.desingBankServicio = desingBankServicio;
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

    public String getJsonDesingBank() {
        return jsonDesingBank;
    }

    public void setJsonDesingBank(String jsonDesingBank) {
        this.jsonDesingBank = jsonDesingBank;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public ParametrosPersonalesOd getParametrosPersonales() {
        return parametrosPersonales;
    }

    public void setParametrosPersonales(ParametrosPersonalesOd parametrosPersonales) {
        this.parametrosPersonales = parametrosPersonales;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public ParametrosPersonalesOd getParametrosPersonalesBase() {
        return parametrosPersonalesBase;
    }

    public void setParametrosPersonalesBase(ParametrosPersonalesOd parametrosPersonalesBase) {
        this.parametrosPersonalesBase = parametrosPersonalesBase;
    }
}





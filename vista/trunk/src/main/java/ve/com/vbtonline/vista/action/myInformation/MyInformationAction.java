package ve.com.vbtonline.vista.action.myInformation;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.venezolano.util.text.NullFormatter;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.myInformation.IMyInformationServicio;
import ve.com.vbtonline.servicio.od.*;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Created by IntelliJ IDEA.
 * User: Rafael Godoy
 * Date: 13/07/2012
 * Time: 05:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyInformationAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(MyInformationAction.class);
    private FabricaTo fabrica;
    private MyInformationOd myInformation;
    private IMyInformationServicio myInformationServicio;
    private String mensaje;
    private DataJson data=new DataJson();
    private String jsonMyInformation;
    private Map session;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasTelefono;
    private List<ContentTableInfoOd> contenidoTabla_infoTelefono;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasDireccion;
    private List<ContentTableInfoOd> contenidoTabla_infoDireccion;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasDocumentos;
    private List<ContentTableInfoOd> contenidoTabla_infoDocumentos;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasRepresentantes;
    private List<ContentTableInfoOd> contenidoTabla_infoRepresentantes;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasContactos;
    private List<ContentTableInfoOd> contenidoTabla_infoContactos;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasCarteras;
    private List<ContentTableInfoOd> contenidoTabla_infoCarteras;
    private List<String> detalleCliente;
    private String idioma;
    private String linkActualizacion;
    private String mostrarActualizacion;



    public MyInformationAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }



    public String execute() throws Exception {
        final String origen = "myInformationAction.execute";
        long time;
        MyInformationOd myInformation;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MyInformationAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MyInformationAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }


    public String cargar() throws Exception {
        final String origen = "myInformationAccion.cargar";
        long time;
        MyInformationOd myInformation;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MyInformationAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonMyInformation, DataJson.class);
            myInformation=getMyInformationServicio().cargar("", data.getMyInformations().get(0), usuario);
            setMensaje(myInformation.getId().toString());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MyInformationAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarMiInformacion() throws Exception {
        final String origen = "myInformationAction.cargarMiInformacion";
        long time;
        List<String> parametros;
        VBTUsersOd usuario = new VBTUsersOd();
        String carteras = new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MyInformationAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            idioma = (String) session.get("idioma");
//
            usuario = (VBTUsersOd) session.get("user");
            carteras = (String) session.get("carteras");
            parametros= new ArrayList<>();
            parametros.add(usuario.getCodpercli());
            TableOd tabla = getMyInformationServicio().consultarTelefonos(parametros, idioma, usuario);
            contenidoTabla_culumnasTelefono=tabla.getContenidoTabla_culumnas();
            contenidoTabla_infoTelefono=tabla.getContenidoTabla_info();

            tabla = new TableOd();
            parametros = new ArrayList<>();
            parametros.add(usuario.getCodpercli());
            tabla = getMyInformationServicio().consultarDirecciones(parametros, idioma, usuario);
            contenidoTabla_culumnasDireccion=tabla.getContenidoTabla_culumnas();
            contenidoTabla_infoDireccion=tabla.getContenidoTabla_info();

           /* tabla = new TableOd();
            parametros = new ArrayList<>();
            parametros.add(usuario.getCodpercli());
            tabla = getMyInformationServicio().consultarDocumentos(parametros, idioma, usuario);
            contenidoTabla_culumnasDocumentos=tabla.getContenidoTabla_culumnas();
            contenidoTabla_infoDocumentos=tabla.getContenidoTabla_info();
            */
            tabla = new TableOd();
            parametros = new ArrayList<>();
            parametros.add(usuario.getCodpercli());
            tabla = getMyInformationServicio().consultarRepresentantes(parametros, idioma, usuario);
            contenidoTabla_culumnasRepresentantes=tabla.getContenidoTabla_culumnas();
            contenidoTabla_infoRepresentantes=tabla.getContenidoTabla_info();

            tabla = new TableOd();
            parametros = new ArrayList<>();
            parametros.add(usuario.getCodpercli());
            tabla = getMyInformationServicio().consultarContactos(parametros, idioma, usuario);
            contenidoTabla_culumnasContactos=tabla.getContenidoTabla_culumnas();
            contenidoTabla_infoContactos=tabla.getContenidoTabla_info();

            tabla = new TableOd();
            TableOdDetallesOd tablaDetalle = new TableOdDetallesOd();
            parametros = new ArrayList<>();
            parametros.add(usuario.getCodpercli());
            parametros.add(carteras);
            tablaDetalle = getMyInformationServicio().consultarCarteras(parametros, idioma, usuario);
            tabla = tablaDetalle.getTabla();
            contenidoTabla_culumnasCarteras=tabla.getContenidoTabla_culumnas();
            contenidoTabla_infoCarteras=tabla.getContenidoTabla_info();

//            detalleCliente = tablaDetalle.getDetalles();

           // if(usuario.getTipo().equals("6") || usuario.getTipo().equals("7") || usuario.getTipo().equals("8") || usuario.getTipo().equals("9")){
                parametros = new ArrayList<>();
                parametros.add(usuario.getNombres());
                parametros.add(usuario.getCirif());
                parametros.add(usuario.getEmail());
                parametros.add("(+"+usuario.getCodigoPais()+") "+usuario.getTelefono_celular());
                detalleCliente = parametros;
        /*    }else{
                detalleCliente = tablaDetalle.getDetalles();
            }  */


            linkActualizacion = (String) session.get("linkActualizacion");
            mostrarActualizacion = (String) session.get("mostrarActualizacion");
            if(!("0").equals(getMostrarActualizacion())){
                if(!NullFormatter.isBlank(linkActualizacion) ){
                    this.setLinkActualizacion(linkActualizacion);
                    session.put("linkActualizacion",linkActualizacion);
                }
            }
            this.setMostrarActualizacion(mostrarActualizacion);
            session.put("mostrarActualizacion",mostrarActualizacion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MyInformationAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public MyInformationOd getMyInformation() {
        return myInformation;
    }

    public void setMyInformation(MyInformationOd myInformation) {
        this.myInformation = myInformation;
    }

    public IMyInformationServicio getMyInformationServicio() {
        return myInformationServicio;
    }

    public void setMyInformationServicio(IMyInformationServicio myInformationServicio) {
        this.myInformationServicio = myInformationServicio;
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

    public String getJsonMyInformation() {
        return jsonMyInformation;
    }

    public void setJsonMyInformation(String jsonMyInformation) {
        this.jsonMyInformation = jsonMyInformation;
    }

    public List<ContentsTableColumnsOd> getContenidoTabla_culumnasTelefono() {
        return contenidoTabla_culumnasTelefono;
    }

    public void setContenidoTabla_culumnasTelefono(List<ContentsTableColumnsOd> contenidoTabla_culumnasTelefono) {
        this.contenidoTabla_culumnasTelefono = contenidoTabla_culumnasTelefono;
    }

    public List<ContentTableInfoOd> getContenidoTabla_infoTelefono() {
        return contenidoTabla_infoTelefono;
    }

    public void setContenidoTabla_infoTelefono(List<ContentTableInfoOd> contenidoTabla_infoTelefono) {
        this.contenidoTabla_infoTelefono = contenidoTabla_infoTelefono;
    }

    public List<ContentsTableColumnsOd> getContenidoTabla_culumnasDireccion() {
        return contenidoTabla_culumnasDireccion;
    }

    public void setContenidoTabla_culumnasDireccion(List<ContentsTableColumnsOd> contenidoTabla_culumnasDireccion) {
        this.contenidoTabla_culumnasDireccion = contenidoTabla_culumnasDireccion;
    }

    public List<ContentTableInfoOd> getContenidoTabla_infoDireccion() {
        return contenidoTabla_infoDireccion;
    }

    public void setContenidoTabla_infoDireccion(List<ContentTableInfoOd> contenidoTabla_infoDireccion) {
        this.contenidoTabla_infoDireccion = contenidoTabla_infoDireccion;
    }

    public List<ContentsTableColumnsOd> getContenidoTabla_culumnasDocumentos() {
        return contenidoTabla_culumnasDocumentos;
    }

    public void setContenidoTabla_culumnasDocumentos(List<ContentsTableColumnsOd> contenidoTabla_culumnasDocumentos) {
        this.contenidoTabla_culumnasDocumentos = contenidoTabla_culumnasDocumentos;
    }

    public List<ContentTableInfoOd> getContenidoTabla_infoDocumentos() {
        return contenidoTabla_infoDocumentos;
    }

    public void setContenidoTabla_infoDocumentos(List<ContentTableInfoOd> contenidoTabla_infoDocumentos) {
        this.contenidoTabla_infoDocumentos = contenidoTabla_infoDocumentos;
    }

    public List<ContentsTableColumnsOd> getContenidoTabla_culumnasRepresentantes() {
        return contenidoTabla_culumnasRepresentantes;
    }

    public void setContenidoTabla_culumnasRepresentantes(List<ContentsTableColumnsOd> contenidoTabla_culumnasRepresentantes) {
        this.contenidoTabla_culumnasRepresentantes = contenidoTabla_culumnasRepresentantes;
    }

    public List<ContentTableInfoOd> getContenidoTabla_infoRepresentantes() {
        return contenidoTabla_infoRepresentantes;
    }

    public void setContenidoTabla_infoRepresentantes(List<ContentTableInfoOd> contenidoTabla_infoRepresentantes) {
        this.contenidoTabla_infoRepresentantes = contenidoTabla_infoRepresentantes;
    }

    public List<ContentsTableColumnsOd> getContenidoTabla_culumnasContactos() {
        return contenidoTabla_culumnasContactos;
    }

    public void setContenidoTabla_culumnasContactos(List<ContentsTableColumnsOd> contenidoTabla_culumnasContactos) {
        this.contenidoTabla_culumnasContactos = contenidoTabla_culumnasContactos;
    }

    public List<ContentTableInfoOd> getContenidoTabla_infoContactos() {
        return contenidoTabla_infoContactos;
    }

    public void setContenidoTabla_infoContactos(List<ContentTableInfoOd> contenidoTabla_infoContactos) {
        this.contenidoTabla_infoContactos = contenidoTabla_infoContactos;
    }

    public List<ContentsTableColumnsOd> getContenidoTabla_culumnasCarteras() {
        return contenidoTabla_culumnasCarteras;
    }

    public void setContenidoTabla_culumnasCarteras(List<ContentsTableColumnsOd> contenidoTabla_culumnasCarteras) {
        this.contenidoTabla_culumnasCarteras = contenidoTabla_culumnasCarteras;
    }

    public List<ContentTableInfoOd> getContenidoTabla_infoCarteras() {
        return contenidoTabla_infoCarteras;
    }

    public void setContenidoTabla_infoCarteras(List<ContentTableInfoOd> contenidoTabla_infoCarteras) {
        this.contenidoTabla_infoCarteras = contenidoTabla_infoCarteras;
    }

    public List<String> getDetalleCliente() {
        return detalleCliente;
    }

    public void setDetalleCliente(List<String> detalleCliente) {
        this.detalleCliente = detalleCliente;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getLinkActualizacion() {
        return linkActualizacion;
    }

    public void setLinkActualizacion(String linkActualizacion) {
        this.linkActualizacion = linkActualizacion;
    }

    public String getMostrarActualizacion() {
        return mostrarActualizacion;
    }

    public void setMostrarActualizacion(String mostrarActualizacion) {
        this.mostrarActualizacion = mostrarActualizacion;
    }
}





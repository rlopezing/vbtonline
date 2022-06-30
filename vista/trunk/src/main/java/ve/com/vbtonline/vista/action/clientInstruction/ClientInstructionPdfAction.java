package ve.com.vbtonline.vista.action.clientInstruction;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 30/09/13
 * Time: 01:11 PM
 * To change this template use File | Settings | File Templates.
 */

// import the iText packages

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.clientInstruction.IClientInstructionServicio;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.vista.action.reportsPdf.ReportUtil;

import javax.servlet.ServletContext;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.apache.commons.codec.binary.Base64.decodeBase64;

/**
 * Created by IntelliJ IDEA.
 * User: Jorge Maguina
 * Date: 28/11/2012
 * Time: 09:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClientInstructionPdfAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {

    private static final Logger logger = Logger.getLogger(ClientInstructionPdfAction.class);
    private FabricaTo fabrica;
    private String mensaje;
    private DataJson data=new DataJson();
    private Map session;
    private IClientInstructionServicio clientInstructionPdfServicio;
    private InputStream inputStream;
    private String reporte;


    //Parameters pdf
    private String client;
    private String id;
    private String transferType;
    private String quantity;
    private String vencimiento;
    private String codperclicarta;
    private String descTipoTransf;

    //motivos para el pdf
    private java.util.List<ContentSelectOd> motivos;

    PdfWriter docWriter = null;

    public ClientInstructionPdfAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }

    public String execute() throws Exception {
        final String origen = "ClientInstructionPdfAction.execute";
        long time;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ClientInstructionPdfAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+ClientInstructionPdfAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }
    /**
     * LISTO
     * Reporte de estado de cuenta
     * @return SUCCESS
     * @throws Exception
     */
    public String generatePdfClientInstruction() throws Exception {
        SelectOd motivos2 = new SelectOd();
        final String origen = "ClientInstructionPdfAction.generatePdfClientInstruction";
        long time;
        Random random = new Random();
        ClientInstructionPDF pdf = null;

        String sessionid = String.valueOf(random.nextInt(2147483647));
        java.util.List<String> numeros = new ArrayList <String>();
        String idioma ="";
        VBTUsersOd user=new VBTUsersOd();
        try{

            ClientInstructionOd  carta = new ClientInstructionOd();
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ClientInstructionPdfAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            user = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            carta.setCliente(StringUtils.newStringUtf8(decodeBase64(client.getBytes())));
            carta.setRif( StringUtils.newStringUtf8(decodeBase64(id.getBytes())));
            carta.setTipoTransf(StringUtils.newStringUtf8(decodeBase64(transferType.getBytes())));
            carta.setMaxcartas(StringUtils.newStringUtf8(decodeBase64(quantity.getBytes())));
            carta.setVencimiento(StringUtils.newStringUtf8(decodeBase64(vencimiento.getBytes())));
            carta.setCodperclicarta(StringUtils.newStringUtf8(decodeBase64(codperclicarta.getBytes())));
            carta.setCodpercli(StringUtils.newStringUtf8(decodeBase64(codperclicarta.getBytes())));
            carta.setDescTipoTransf(StringUtils.newStringUtf8(decodeBase64(descTipoTransf.getBytes())));

            numeros = clientInstructionPdfServicio.insertarNumeroControl( carta, sessionid,  carta.getCodpercli(), user.getIP(), user);

            motivos2 = getClientInstructionPdfServicio().cargarMotivos(user,"_us_en");
            motivos = motivos2.getContenido();
            carta.setMotivos(motivos2.getContenido());

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                if (carta.getTipoTransf().equals("TI")) {
                    pdf = new ClientInstructionInternalPDF();
                } else if (carta.getTipoTransf().equals("TO")) {
                    pdf = new ClientInstructionExternalPDF();
                }

            buffer = pdf.generatePdf(carta, numeros);



            inputStream  =  new ByteArrayInputStream(buffer.toByteArray());

            this.GuardarLog(user.getLogin(),"13","1","11","",user.getIP(),"El usuario: "+user.getLogin()+" generó "+carta.getMaxcartas()+" Carta(s) de Instrucción: "+carta.getDescTipoTransf());

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+ClientInstructionPdfAction.class+" | "+origen+" | "+time);

        }catch (Exception e){

            if(numeros.size()>0){
                try{
                 clientInstructionPdfServicio.eliminarNumeroControl(numeros, user);
                }catch(Exception e1){}
            }
           //e.printStackTrace();

            inputStream=crearDocument_error(idioma);
            logger.error(e);
            setMensaje(e.getMessage());

        }  finally{
            pdf = null;
        }
        return SUCCESS;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "ClientInstructionPdfAction.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ClientInstructionPdfAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getClientInstructionPdfServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+ClientInstructionPdfAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return respuesta;
    }

    public InputStream crearDocument_error(String idioma){
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        // Modo inicial el reporte
        try{
            // Modo inicial el reporte
            ReportUtil reportUtil = new ReportUtil();
            Document document = reportUtil.createDocument("Report");
            PdfWriter writer = PdfWriter.getInstance(document, buffer);

            document.open();

            float[] medidaCeldas = {10f};
            PdfPTable TableCuentaCabecera = reportUtil.crearTabla(1,100,medidaCeldas);  // mueestra el Statement of Account y su respectiva imagen
            TableCuentaCabecera.setHorizontalAlignment(Element.ALIGN_LEFT);
            Paragraph tituloGrande = reportUtil.crearTituloGrande(14,"Error ");
            PdfPCell celltituloGrande = new PdfPCell(tituloGrande);
            celltituloGrande.setHorizontalAlignment(Element.ALIGN_LEFT);
            celltituloGrande.setBorder(Rectangle.NO_BORDER);
            String archivo_temp = ServletActionContext.getServletContext().getRealPath("/");
            archivo_temp += "/resources/images/comun/logo.gif";
            PdfPCell imagenCel_tarjeta = reportUtil.crearImagenCelda(archivo_temp,50,50,100);
            imagenCel_tarjeta.setHorizontalAlignment(Element.ALIGN_LEFT);
            imagenCel_tarjeta.setVerticalAlignment(Element.ALIGN_TOP);
            imagenCel_tarjeta.setBorder(Rectangle.NO_BORDER);
            TableCuentaCabecera.addCell(imagenCel_tarjeta);
            TableCuentaCabecera.addCell(celltituloGrande);
            document.add(TableCuentaCabecera);

            medidaCeldas = new float[]{1.5f};
            PdfPTable TableSeparacion_space= reportUtil.crearTabla(1,100,medidaCeldas);
            TableSeparacion_space.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cellSeparador=new PdfPCell();

            cellSeparador= reportUtil.datosTabla("  ", Element.ALIGN_LEFT);
            TableSeparacion_space.addCell(cellSeparador);

            for(int s=1;s<3;s++)
                document.add(TableSeparacion_space);

            float[] medidaCeldas2 = {10f};
            PdfPTable TableCuentaCabecera2 = reportUtil.crearTabla(1,100,medidaCeldas);  // mueestra el Statement of Account y su respectiva imagen
            //  TableCuentaCabecera.setHorizontalAlignment(Element.ALIGN_LEFT);
            /*try{
            System.out.println(ResourceBundle.getBundle("Comun"+idioma).getString("TAGErrorPDF"));
            }catch(Exception e){
                System.out.println("falla");
            }
                  System.out.println("1");*/
            PdfPCell  celldatos = new PdfPCell(reportUtil.formatoValor(8,ResourceBundle.getBundle("Comun"+idioma).getString("TAGErrorPDF")));
            celldatos.setColspan(2);
            celldatos.setBorder(Rectangle.NO_BORDER);
            TableCuentaCabecera2.addCell(celldatos);


            document.add(TableCuentaCabecera2);

            document.close();

        }catch (Exception e) {

        }

        return inputStream  =  new ByteArrayInputStream(buffer.toByteArray());
    }


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public IClientInstructionServicio getClientInstructionPdfServicio() {
        return clientInstructionPdfServicio;
    }

    public void setClientInstructionPdfServicio(IClientInstructionServicio clientInstructionPdfServicio) {
        this.clientInstructionPdfServicio = clientInstructionPdfServicio;
    }

    public String getCodperclicarta() {
        return codperclicarta;
    }

    public void setCodperclicarta(String codperclicarta) {
        this.codperclicarta = codperclicarta;
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getDescTipoTransf() {
        return descTipoTransf;
    }

    public void setDescTipoTransf(String descTipoTransf) {
        this.descTipoTransf = descTipoTransf;
    }
}





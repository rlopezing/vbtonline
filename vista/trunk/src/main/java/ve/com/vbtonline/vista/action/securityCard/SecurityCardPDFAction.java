package ve.com.vbtonline.vista.action.securityCard;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.securityCard.ISecurityCardServicio;
import ve.com.vbtonline.servicio.od.SecurityCardOd;
import ve.com.vbtonline.servicio.od.VBTUsersOd;
import ve.com.vbtonline.vista.action.reportsPdf.ReportUtil;

import javax.servlet.ServletContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 20/09/13
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityCardPDFAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {

    private static final Logger logger = Logger.getLogger(SecurityCardPDFAction.class);
    private ISecurityCardServicio securityCardPDFServicio;

    private Map session;
    private PdfWriter docWriter = null;
    private InputStream inputStream;
    private String idioma;
    private String codigo;
    private String mensaje;



    private int  TAM_LETRA_CONTENIDO = 10;
    private int  TAM_LETRA_SUBTITULO = 12;

    public SecurityCardPDFAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }


    public String execute() throws Exception {
        final String origen = "SecurityCardAction.execute";
        long time;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityCardPDFAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityCardPDFAction.class+" | "+origen+" | "+time);

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
    public String generatePdfSecurityCard() throws Exception {

        //reporte="EDC";
        final String origen = "SecurityCardAction.generatePdfSecurityCard";
        long time;


        BaseColor colorLetra = new BaseColor(0, 0, 0);//new Color(245,245,245);
        BaseColor colorLetraSerial   = new BaseColor(25,82,102);
        BaseColor colorFondoTitulo   = new BaseColor(25,82,102);
        BaseColor colorLetraTitulo   = new BaseColor(245,245,245);

        //Evento de celda para agregar el fondo con bordes redondeados
        PdfPCellEvent roundRectangle = new RoundRectangle(BaseColor.GREEN);
        PdfPCellEvent blackRectangle = new RoundRectangle(BaseColor.BLACK);
        PdfPCellEvent tituloCellBackground = new CellBackground(colorFondoTitulo);
        PdfPCellEvent cellBackground = new CellBackground(BaseColor.WHITE);
        PdfPTableEvent tableBackground = new TableBackground(BaseColor.WHITE);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        Date today = new Date();

        Chunk chunk = null;
        PdfPTable pdftable = null;
        PdfPCell cell=null;
        Phrase phrase = null;

        Document document = new Document(PageSize.LETTER, 60, 60, 60, 60);
        try{


            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + SecurityCardPDFAction.class + " | " + origen);
            time = System.currentTimeMillis ();

            VBTUsersOd user = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");

            docWriter = PdfWriter.getInstance(document, buffer);
            document.addCreator("VBT Bank & Trust Online");
            document.addCreationDate();
            document.addAuthor("VBT Bank & Trust Online");
            //Paso 3: Abro el documento
            document.open();
            //////////////////////////////////////////////////////////////////////

            //Definos las letras
            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);

            String pathTijeras = ServletActionContext.getServletContext().getRealPath("/");
             pathTijeras +=  "resources/images/tijeras.gif";
            Image imgTijeras = Image.getInstance(pathTijeras);

            float ancho_tablas = 90f;
            SecurityCardOd tarjeta = new SecurityCardOd();

            tarjeta = securityCardPDFServicio.generarTarjeta(tarjeta, user.getLogin(), user.getIP(), user); //PARA PRUEBAS
            logger.info("5");



                //COORDENADAS
                ArrayList filas = tarjeta.getFilas();
                ArrayList columnas = tarjeta.getColumnas();
            int columnasSize= columnas.size();
            int filasSize= filas.size();
                document.newPage();
                pdftable = new PdfPTable(columnasSize + 1);
                pdftable.setWidthPercentage(80);
                pdftable.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdftable.setTableEvent(tableBackground);
               try{
                //Para generar los titulos de las columnas
                phrase = new Phrase(new Chunk(" ",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetraTitulo)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setCellEvent(cellBackground);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setLeading(9, 0);
                pdftable.addCell(cell);


                for (int c=0; c<columnasSize; c++) {
                    phrase = new Phrase(new Chunk((String)columnas.get(c),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetraTitulo)));
                    cell=new PdfPCell(phrase);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setCellEvent(tituloCellBackground);
                    cell.setCellEvent(roundRectangle);
                    cell.setBorder(PdfPCell.NO_BORDER);
                    cell.setPaddingBottom(1*(cell.getPhrase().getFont().getSize()-BaseFont.CAPHEIGHT));
                    cell.setLeading(9, 0);
                    pdftable.addCell(cell);
                }

                for (int f=0; f<filasSize; f++) {
                    phrase = new Phrase(new Chunk((String)filas.get(f),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetraTitulo)));
                    cell=new PdfPCell(phrase);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setCellEvent(tituloCellBackground);
                    cell.setCellEvent(roundRectangle);
                    cell.setBorder(PdfPCell.NO_BORDER);
                    cell.setPaddingBottom(1*(cell.getPhrase().getFont().getSize()-BaseFont.CAPHEIGHT));
                    cell.setLeading(9, 0);
                    pdftable.addCell(cell);

                    for (int c=0; c<columnasSize; c++) {
                        phrase = new Phrase(new Chunk((String)tarjeta.getValor((String)filas.get(f), (String)columnas.get(c)),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                        cell=new PdfPCell(phrase);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setCellEvent(cellBackground);
                        cell.setCellEvent(roundRectangle);
                        cell.setBorder(PdfPCell.NO_BORDER);
                        cell.setPaddingBottom(1*(cell.getPhrase().getFont().getSize()-BaseFont.CAPHEIGHT));
                        cell.setLeading(9, 0);
                        pdftable.addCell(cell);
                    }
                }
               }catch(Exception e){
                                     e.printStackTrace();
               }
                PdfPTable pdftable0 = new PdfPTable(3);
                pdftable0.setWidthPercentage(100);
                pdftable0.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdftable0.setTableEvent(tableBackground);

                cell=new PdfPCell(pdftable);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setColspan(3);
                cell.setBorder(PdfPCell.NO_BORDER);
                pdftable0.addCell(cell);

                phrase = new Phrase(new Chunk("Serial: " + tarjeta.getSerial(),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetraSerial)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setColspan(2);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingBottom(1*(cell.getPhrase().getFont().getSize()-BaseFont.CAPHEIGHT));
                cell.setPaddingRight(1);
                cell.setLeading(10, 0);
                pdftable0.addCell(cell);
                phrase = new Phrase(new Chunk(tarjeta.getVencimiento(),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetraSerial)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPaddingBottom(1*(cell.getPhrase().getFont().getSize()-BaseFont.CAPHEIGHT));
                cell.setPaddingLeft(1);
                cell.setLeading(10, 0);
                pdftable0.addCell(cell);

                float tamaños_tabla[] = {2f,88f,2f};

                PdfPTable pdftable1 = new PdfPTable(3);
                pdftable1.setWidthPercentage(80);
            //pdftable1.setTotalWidth(500);

                pdftable1.setWidths(tamaños_tabla);
                pdftable1.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdftable1.setTableEvent(tableBackground);

                phrase = new Phrase(new Chunk(" ",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetraTitulo)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setColspan(3);
                cell.setLeading(1, 0);
                pdftable1.addCell(cell);

                phrase = new Phrase(new Chunk(" ",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetraTitulo)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(PdfPCell.NO_BORDER);
                pdftable1.addCell(cell);



                cell=new PdfPCell(pdftable0);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setCellEvent(blackRectangle);
                cell.setBorder(PdfPCell.NO_BORDER);
                pdftable1.addCell(cell);



                phrase = new Phrase(new Chunk(" ",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetraTitulo)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(PdfPCell.NO_BORDER);
                pdftable1.addCell(cell);

                phrase = new Phrase(new Chunk(" ",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetraTitulo)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setColspan(3);
                cell.setLeading(1, 0);
                pdftable1.addCell(cell);

                PdfPTable pdftable2 = new PdfPTable(1);
             pdftable2.setWidthPercentage(52);


                pdftable2.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdftable2.setTableEvent(tableBackground);

                cell=new PdfPCell(pdftable1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                PdfPCellEvent interlineado = new CustomCell(BaseColor.BLACK,imgTijeras);
                cell.setCellEvent(interlineado);
                cell.setBorder(PdfPCell.NO_BORDER);
                pdftable2.addCell(cell);
            document.add(pdftable2);
//*****//

            Paragraph p  = new Paragraph((new Chunk(" ")));
            document.add(p);

            p  = new Paragraph((new Chunk(ResourceBundle.getBundle("vbtonline"+idioma).getString("TagTituloInstruccionesTCO"),new Font(bfArial, TAM_LETRA_SUBTITULO, Font.BOLD, colorLetraSerial))));
            p.setAlignment(Element.ALIGN_CENTER);

            List orderedList = new List(List.ORDERED);
            orderedList.add(new ListItem(new Chunk(ResourceBundle.getBundle("vbtonline"+idioma).getString("InstruccionesTCO1"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra))));
            orderedList.add(new ListItem(new Chunk(ResourceBundle.getBundle("vbtonline"+idioma).getString("InstruccionesTCO2"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra))));
            orderedList.add(new ListItem(new Chunk(ResourceBundle.getBundle("vbtonline"+idioma).getString("InstruccionesTCO3"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra))));
            orderedList.add(new ListItem(new Chunk(ResourceBundle.getBundle("vbtonline"+idioma).getString("InstruccionesTCO4"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra))));
            orderedList.add(new ListItem(new Chunk(ResourceBundle.getBundle("vbtonline"+idioma).getString("InstruccionesTCO5"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra))));
            orderedList.add(new ListItem(new Chunk(ResourceBundle.getBundle("vbtonline"+idioma).getString("InstruccionesTCO6"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra))));


            p.add(orderedList);
            document.add(p);



            PdfPTable pdftableInstrucciones = new PdfPTable(3);
            float tamaños_tabla2[] = {47,6,47};
            pdftableInstrucciones.setWidthPercentage(100);

            pdftableInstrucciones.setWidths(tamaños_tabla2);
            pdftableInstrucciones.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdftableInstrucciones.setTableEvent(tableBackground);

            phrase = new Phrase(new Chunk(" ",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
            cell=new PdfPCell(phrase);
            cell.setColspan(3);
            cell.setBorder(PdfPCell.NO_BORDER);
            pdftableInstrucciones.addCell(cell);


            cell=new PdfPCell();
            p  = new Paragraph((new Chunk(ResourceBundle.getBundle("vbtonline"+idioma).getString("TagTituloUsoTCO"),new Font(bfArial, TAM_LETRA_SUBTITULO, Font.BOLD, colorLetraSerial))));
            cell.addElement(p);

            Paragraph p1  = new Paragraph((new Chunk(ResourceBundle.getBundle("vbtonline"+idioma).getString("UsoTCO1"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra))));
            Paragraph p2  = new Paragraph((new Chunk(ResourceBundle.getBundle("vbtonline"+idioma).getString("UsoTCO2"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra))));


            p1.setAlignment(Element.ALIGN_JUSTIFIED);
            p2.setAlignment(Element.ALIGN_JUSTIFIED);

            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setVerticalAlignment(Element.ALIGN_TOP);
            cell.setBorder(PdfPCell.NO_BORDER);


            cell.addElement(p1);
            cell.addElement(p2);


            pdftableInstrucciones.addCell(cell);

            cell=new PdfPCell();
            p  = new Paragraph((new Chunk("")));



            cell.setBorder(PdfPCell.NO_BORDER);
            pdftableInstrucciones.addCell(cell);



            cell=new PdfPCell();
            p  = new Paragraph((new Chunk(ResourceBundle.getBundle("vbtonline"+idioma).getString("TagTituloRecomendacionesTCO"),new Font(bfArial, TAM_LETRA_SUBTITULO, Font.BOLD, colorLetraSerial))));
            //p.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(p);


            List unorderedList = new List(List.UNORDERED);
            ListItem listItem = new ListItem(ResourceBundle.getBundle("vbtonline"+idioma).getString("RecomendacionesTCO1"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra));
            listItem.setAlignment(Element.ALIGN_JUSTIFIED);
            unorderedList.add(listItem);

            listItem  = new ListItem(ResourceBundle.getBundle("vbtonline"+idioma).getString("RecomendacionesTCO2"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra));
            listItem.setAlignment(Element.ALIGN_JUSTIFIED);
            unorderedList.add(listItem);
            listItem  =  new ListItem(ResourceBundle.getBundle("vbtonline"+idioma).getString("RecomendacionesTCO3"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra));
            listItem.setAlignment(Element.ALIGN_JUSTIFIED);
            unorderedList.add(listItem);

            listItem  =  new ListItem(ResourceBundle.getBundle("vbtonline"+idioma).getString("RecomendacionesTCO4"),new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra));
            listItem.setAlignment(Element.ALIGN_JUSTIFIED);
            unorderedList.add(listItem);


            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setVerticalAlignment(Element.ALIGN_TOP);
            cell.setBorder(PdfPCell.NO_BORDER);

            cell.addElement(unorderedList);
            pdftableInstrucciones.addCell(cell);







            document.add(pdftableInstrucciones);
        //document.add(orderedList)       ;    */

                document.close();
                inputStream  =  new ByteArrayInputStream(buffer.toByteArray());
                setCodigo("OK");
            this.GuardarLog(user.getLogin(),"13","1","19","",user.getIP(),"Usuario generó PDF de la tarjeta de coordenadas ");

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityCardPDFAction.class+" | "+origen+" | "+time);

        } catch (Exception e){


            // e.printStackTrace();
            setCodigo("ERROR");
            setMensaje("ERROR AL GENERAR TARJETA");
            setInputStream(crearDocument_error(idioma));
            logger.error(e);
            setMensaje(e.getMessage());

        }  finally{


        }
        return SUCCESS;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "SecurityCardPDFAction.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityCardPDFAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getSecurityCardPDFServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityCardPDFAction.class+" | "+origen+" | "+time);

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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ISecurityCardServicio getSecurityCardPDFServicio() {
        return securityCardPDFServicio;
    }

    public void setSecurityCardPDFServicio(ISecurityCardServicio securityCardPDFServicio) {
        this.securityCardPDFServicio = securityCardPDFServicio;
    }

    /**
 * Inner class with a cell event that draws a border with rounded corners.
 */
class RoundRectangle implements PdfPCellEvent {
    private BaseColor bgColor;

    public RoundRectangle(BaseColor bgColor) {
        this.bgColor = bgColor;
    }

    public void cellLayout(PdfPCell cell, Rectangle rect,
                           PdfContentByte[] canvas) {
        PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
        cb.roundRectangle(
                rect.getLeft() + 1.5f, rect.getBottom() + 1.5f, rect.getWidth() - 3,
                rect.getHeight() - 3, 4);
        cb.setLineWidth(1.5f);
        cb.setColorFill(bgColor);
        cb.stroke();
    }
}

/**
 * Inner class with a table event that draws a background with rounded corners.
 */
class TableBackground implements PdfPTableEvent {
    private BaseColor bgColor;

    public TableBackground(BaseColor bgColor) {
        this.bgColor = bgColor;
    }

    public void tableLayout(PdfPTable table, float[][] width, float[] height,
                            int headerRows, int rowStart, PdfContentByte[] canvas) {
        PdfContentByte background = canvas[PdfPTable.BASECANVAS];
        background.saveState();
        background.setCMYKColorFill(0x00, 0x00, 0xFF, 0x0F);
        background.roundRectangle(
                width[0][0], height[height.length - 1] - 2,
                width[0][1] - width[0][0] + 6, height[0] - height[height.length - 1] - 4, 4);
        background.setColorFill(bgColor);
        background.restoreState();
    }

}

/**
 * Inner class with a cell event that draws a background with rounded corners.
 */
class CellBackground implements PdfPCellEvent {
    private BaseColor bgColor;

    public CellBackground(BaseColor bgColor) {
        this.bgColor = bgColor;
    }

    public void cellLayout(PdfPCell cell, Rectangle rect,
                           PdfContentByte[] canvas) {
        PdfContentByte cb = canvas[PdfPTable.BACKGROUNDCANVAS];
        cb.roundRectangle(
                rect.getLeft() + 1.5f, rect.getBottom() + 1.5f, rect.getWidth() - 3,
                rect.getHeight() - 3, 4);
        cb.setColorFill(bgColor);
        cb.fill();
    }
}

class CustomCell implements PdfPCellEvent {
    private BaseColor bgColor;
    private Image image;

    public CustomCell(BaseColor bgColor, Image image) {
        this.bgColor = bgColor;
        this.image = image;
    }

    public void cellLayout(PdfPCell cell, Rectangle rect,
                           PdfContentByte[] canvas) {
        PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
        cb.roundRectangle(
                rect.getLeft() + 1.5f, rect.getBottom() + 1.5f, rect.getWidth() - 3,
                rect.getHeight() - 3, 4);
        cb.setLineWidth(1.5f);
        cb.setLineDash(new float[] {3.0f, 3.0f}, 0);
        cb.setColorFill(bgColor);
        cb.stroke();

        PdfContentByte cbBg = canvas[PdfPTable.BACKGROUNDCANVAS];
        try {
            cbBg.saveState();
            image.scalePercent(6f);
            image.setAlignment(Image.MIDDLE);
            //image.setAbsolutePosition(rect.getRight() - (6*image.getWidth()/100)/2, rect.getBottom() + (6*image.getHeight()/100)/2);
            image.setAbsolutePosition(rect.getRight() - ((6*image.getWidth()/100)/2), rect.getBottom() - ((6*image.getHeight()/100)/2) + 2);
            cbBg.addImage(image);
            cbBg.restoreState();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
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
}






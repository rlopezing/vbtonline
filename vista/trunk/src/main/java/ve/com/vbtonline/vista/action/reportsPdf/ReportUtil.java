package ve.com.vbtonline.vista.action.reportsPdf;


import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/**
 * Created by IntelliJ IDEA.
 * User: Jorge
 * Date: 02/12/2012
 * Time: 10:28:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportUtil {

    private final int TAM_LETRA_TITULO = 18;
    private final int TAM_LETRA_SUBTITULO = 12;
    private final int TAM_LETRA_CONTENIDO = 8;
    private final int TAM_LETRA_CONTENIDO_CERTIFICADO = 10;
    BaseColor colorLetra = new BaseColor(0, 0, 0);
    /**
     * Gnera encabezado de tabla con color gris
     * @param valor
     * @return
     */
    public PdfPCell encabezadoTablaGris(String valor){

        PdfPCell cell = new PdfPCell();
//        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Phrase frase =new Phrase();
        frase =new Phrase();
        try{
            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            frase.setFont(new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra));

        }catch(Exception ex){


        }



        frase.add(new Chunk(valor));
        cell.setPhrase(frase);
        return cell;
    }

    /**
     * Coloca imagen en una celda
     * @param nombreImag
     * @param escalax
     * @param escalay
     * @param porc
     * @return
     * @throws IOException
     * @throws BadElementException
     */
    public PdfPCell crearImagenCelda(String nombreImag,int escalax,int escalay,int porc) throws IOException, BadElementException {

        Image imagen = Image.getInstance(nombreImag);
        imagen.scaleToFit(escalax, escalay);
        imagen.scalePercent(porc);
        PdfPCell cell=new PdfPCell(imagen);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    /**
     * Genera celdas con informacion sin  bordes
     * @param valor
     * @param horizontalAlign
     * @return
     */
    public PdfPCell datosTabla(String valor , int horizontalAlign){
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(horizontalAlign);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Phrase frase =new Phrase();
        try{

            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            frase.setFont(new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra));
            frase.add(new Chunk(valor));
            cell.setPhrase(frase);
        }catch(Exception ex){


        }


        return cell;

    }

    public PdfPCell datosTablaCertificados(String valor , int horizontalAlign){
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(horizontalAlign);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Phrase frase =new Phrase();
        try{

            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            frase.setFont(new Font(bfArial, TAM_LETRA_CONTENIDO_CERTIFICADO, Font.NORMAL, colorLetra));
            frase.add(new Chunk(valor));
            cell.setPhrase(frase);
        }catch(Exception ex){


        }


        return cell;

    }

    public PdfPCell datosTablaCertificadosNegrita(String valor , int horizontalAlign){
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(horizontalAlign);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Phrase frase =new Phrase();
        try{

            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            frase.setFont(new Font(bfArial, TAM_LETRA_CONTENIDO_CERTIFICADO, Font.BOLD, colorLetra));
            frase.add(new Chunk(valor));
            cell.setPhrase(frase);
        }catch(Exception ex){


        }


        return cell;

    }



    /**
     * Genera celdas con informacion sin  bordes
     * @param valor
     * @param horizontalAlign
     * @return
     */
    public PdfPCell datosTabla_7(String valor , int horizontalAlign){

        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(horizontalAlign);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Phrase frase =new Phrase();
        try{

            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            frase.setFont(new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra));

        }catch(Exception ex){


        }

        frase.add(new Chunk(valor));
        cell.setPhrase(frase);
        return cell;

    }

    public PdfPCell datosTabla_8(String valor , int horizontalAlign){

        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(horizontalAlign);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Phrase frase =new Phrase();
        try{

            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            frase.setFont(new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra));

        }catch(Exception ex){


        }

        frase.add(new Chunk(valor));
        cell.setPhrase(frase);
        return cell;

    }

    public PdfPCell datosTabla_9(String valor , int horizontalAlign){

        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(horizontalAlign);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Phrase frase =new Phrase();
        try{

            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            frase.setFont(new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra));

        }catch(Exception ex){


        }

        frase.add(new Chunk(valor));
        cell.setPhrase(frase);
        return cell;

    }
    /**
     * Genera celdas con informacion sin  bordes
     * @param valor
     * @param horizontalAlign
     * @return
     */
    public PdfPCell datosTabla(String valor , int horizontalAlign, int fuente){

        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(horizontalAlign);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Phrase frase =new Phrase();
        try{

            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            frase.setFont(new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra));

        }catch(Exception ex){


        }

        frase.add(new Chunk(valor));
        cell.setPhrase(frase);
        return cell;

    }



    public PdfPCell datosTablaGris(String valor , int horizontalAlign, int fuente){

        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(horizontalAlign);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Phrase frase =new Phrase();
        try{

            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            frase.setFont(new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra));

        }catch(Exception ex){


        }

        frase.add(new Chunk(valor));
        cell.setPhrase(frase);
        return cell;

    }

    /**
     * Genera encabezados sin gris y sin bordes
     * @param valor
     * @param horizontalAlign
     * @return
     */

    public PdfPCell datosTablaEncabezado(String valor , int horizontalAlign){

        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(horizontalAlign);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        Phrase frase =new Phrase();
        frase.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK));
        frase.add(new Chunk(valor));
        cell.setPhrase(frase);
        return cell;

    }

    public PdfPCell datosTabla(String valor){

        PdfPCell cell = new PdfPCell();
//        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.NO_BORDER);
        Phrase frase =new Phrase();
        frase =new Phrase();
        try{

            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            frase.setFont(new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra));

        }catch(Exception ex){


        }

        frase.add(new Chunk(valor));
        cell.setPhrase(frase);


        return cell;

    }

    /**
     * Crea el documento
     * @param nombre
     * @return
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public Document  createDocument(String nombre) throws FileNotFoundException, DocumentException {

        //se crea el document
        Document document = new Document(PageSize.LETTER,20,20,35,40);

        //ruta donde se genera el document
        FileOutputStream ficheroPdf = new FileOutputStream(nombre);

        // Se asocia el document al OutputStream y se indica que el espaciado entre
        // lineas sera de 20. Esta llamada debe hacerse antes de abrir el document
        PdfWriter writer = PdfWriter.getInstance(document, ficheroPdf);

        //LEADING = separacion entre lineas del document
        writer.setInitialLeading(16);
        Rectangle rct = new Rectangle(36, 54, 559, 788);
        Font font1 = new Font(Font.FontFamily.HELVETICA  , 25, Font.BOLD);
        Font font2 = new Font(Font.FontFamily.COURIER    , 18,
                Font.ITALIC | Font.UNDERLINE);
        Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 27);
        //Definimos un nombre y un tama�o para el PageBox los nombres posibles son: "crop", "trim", "art" and "bleed".
        writer.setBoxSize("art", rct);

        //se le dan valores el document
        document.addAuthor("VBT Bank & Trust Online");
        document.addCreator("VBT Bank & Trust Online");
        document.addTitle("VBT Bank & Trust Online");

        return document;
    }


    /**
     * Crea tabla en el documento
     * @param col
     * @param porc
     * @param medidas
     * @return
     * @throws DocumentException
     */
    public PdfPTable crearTabla(int col,int porc,float [] medidas) throws DocumentException {


        PdfPTable Table=new PdfPTable(col);
        Table.setWidthPercentage(porc);
        Table.setWidths(medidas);
        return Table;

    }


    public Paragraph crearTituloGrande(int tamano,String titulo){
        Paragraph paragraph = new Paragraph(12);
        Chunk chunk=new Chunk(titulo);
        Font font = FontFactory.getFont("Times-Roman",tamano,Font.BOLD);

        try{
            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            chunk.setFont(new Font(bfArial, tamano, Font.BOLD, colorLetra));

        }catch(Exception ex){
        }

        paragraph.add(chunk);

        return paragraph;
    }

    public Paragraph formatoValor(int tamano,String dato){
        Paragraph paragraph = new Paragraph(12);
        Chunk chunk=new Chunk(dato);


        try{

            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";
            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            chunk.setFont(new Font(bfArial, tamano, Font.NORMAL, colorLetra));
        }catch(Exception ex){


        }


        paragraph.add(chunk);

        return paragraph;
    }

    /**
     * Separador
     * @return
     */
    public Chunk separadorLigero(){
        String SEPARADOR = "         _______________________________________________________________________________";

        Chunk chunkSeparador =  new Chunk(SEPARADOR);
        return chunkSeparador;
    }


    public Chunk separadorLimpio(){
        String SEPARADOR = "         ";

        Chunk chunkSeparador =  new Chunk(SEPARADOR);
        return chunkSeparador;
    }

    public PdfPCell encabezadoTablaAzul(String valor , String corner){

        PdfPCell cell = new PdfPCell();
//        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBackgroundColor(new BaseColor(52,103,103));
        cell.setFixedHeight(40f);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        Phrase frase =new Phrase();
        frase =new Phrase();

        if (corner.equalsIgnoreCase("left")){
            frase.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD, BaseColor.WHITE));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        }else{
            frase.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.WHITE));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        }
        frase.add(new Chunk(valor));
        cell.setPhrase(frase);
        return cell;
    }

    /**
     * Este genera encabezados informativos franja gris
     * @param valor
     * @return
     */
    public PdfPTable encabezadoIndice(String valor){
        try{
            float[] medidaCeldas = new float[]{1.5f};
            PdfPTable TableCliente= this.crearTabla(1,90,medidaCeldas);

            PdfPCell cell=new PdfPCell();

            cell= this.encabezadoTablaGris(valor);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setBorder(Rectangle.NO_BORDER);
            TableCliente.addCell(cell);

            return TableCliente;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return new PdfPTable(1);
        }

    }



}

/**
 * Generador de eventos sobre la tabla aca se uede colocar headers y footers
 */
class TableHeader extends PdfPageEventHelper {
    /** The header text. */
    String header;
    /** The template with the total number of pages. */
    PdfTemplate total;

    /**
     * Allows us to change the content of the header.
     * @param header The new header String
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Creates the PdfTemplate that will hold the total number of pages.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }

    /**
     * Adds a header to every page
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(1);
        //mbernott cambio auditor
        String feetFooter = "Please review and report any discrepancies to our auditors Grant Thornton, P.O. Box 1044, Grand Cayman, Cayman Islands, KY1-1102 (to the attention of Cormac Golden [phone +1 345 949 8588]), or alternatively, you could communicate any exceptions by email to Cormac.Golden@ky.gt.com  / Desigan.Thaver@ky.gt.com.";
        //String feetFooter = "Please review and report any discrepancies to our auditors KPMG, P.O. Box 493 GT, Grand Cayman, Cayman Islands, KY1-1106 (to the attention of Thomas Marks [phone +1 345 815 26 54] / Ranga Bernanduge [phone +1 345 914 47 43]), or alternatively, you could communicate any exceptions by email to thomasmarks@kpmg.ky / rbernanduge@kpmg.ky.";
        //String feetFooter = "Please review and report any discrepancies to our auditors KPMG, P.O. Box 493 GT, Grand Cayman, Cayman Islands, KY1-1106 (to the attention of Thomas Marks [phone +1 345 815 26 54] / Joseph Muhia [phone +1 345 916 68 58]), or alternatively, you could communicate any exceptions by email to thomasmarks@kpmg.ky / josephmuhia1@kpmg.ky.";
        /*String feetFooter = "Please review and report any discrepancies to our auditors KPMG, P.O. Box 493 GT, Grand Cayman, Cayman Islands, KY1-1106 (to the attention of Curran Patel [phone +1 345 914 44 22] / Angelica Lejarde [phone +1 345 914 43 62]), or alternately, you could communicate any exceptions by email to curranpatel1@kpmg.ky /alejarde@kpmg.ky.";/*"PLEASE REVIEW AND REPORT ANY DISCREPANCIES TO OUR AUDITORS DELOITTE & TOUCHE AT ONE CAPITAL PLAZA, P.O. BOX 1787, GRAND CAYMAN "
                + "KY1-1109, CAYMAN ISLANDS. TO THE ATTENTION OF JAMIE FEARN EMAIL JFEARN@DELOITTE.COM TEL NUMBER: (345) 814-3436 AND "
                + "FAX NUMBER: (345) 949-8238.\n";  */
        try {
//                table.setWidths(new int[]{24});
            table.setTotalWidth(585);
            table.setLockedWidth(true);
//                table.getDefaultCell().setFixedHeight(20);
            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
            table.addCell(header);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(feetFooter);

            PdfPCell cell = new PdfPCell(Image.getInstance(total));
            //cell.setBorder(Rectangle.BOTTOM);
//            table.addCell(cell);
//            table.writeSelectedRows(0,-1, 10, 100, writer.getDirectContent());

        }
        catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    /**
     * Fills out the total number of pages before the document is closed.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                2, 2, 0);
    }


}


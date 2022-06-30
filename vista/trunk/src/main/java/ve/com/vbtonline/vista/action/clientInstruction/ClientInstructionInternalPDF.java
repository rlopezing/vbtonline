package ve.com.vbtonline.vista.action.clientInstruction;

import com.itextpdf.text.*;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.*;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.clientInstruction.ClientInstructionServicio;
import ve.com.vbtonline.servicio.negocio.clientInstruction.IClientInstructionServicio;
import ve.com.vbtonline.servicio.od.ClientInstructionOd;
import ve.com.vbtonline.servicio.od.ContentSelectOd;
import ve.com.vbtonline.servicio.od.DataJson;
import ve.com.vbtonline.servicio.util.validacionUtil;
import ve.com.vbtonline.vista.action.reportsPdf.ReportUtil;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 02/10/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClientInstructionInternalPDF implements ClientInstructionPDF, Serializable {

    private static final Logger logger = Logger.getLogger(ClientInstructionInternalPDF.class);

    private InputStream inputStream;
    PdfWriter docWriter = null;

    private final int TAM_LETRA_TITULO = 13;
    private final int TAM_LETRA_SUBTITULO = 12;
    private final int TAM_LETRA_CONTENIDO = 10;
    private final int TAM_LETRA_NOTA = 8;
    /**
     * Constructor de la clase
     */
    public ClientInstructionInternalPDF() {
    }

    /*public ByteArrayOutputStream generatePdf(ClientInstructionOd carta, java.util.List<String> numeros,java.util.List<ContentSelectOd> motivos) throws Exception{
        return null;
    }  */

    public ByteArrayOutputStream generatePdf(ClientInstructionOd carta,  java.util.List<String> numeros) throws Exception {
        //reporte="EDC";
        final String origen = "ClientInstructionInternalPDF.generatePdf";
        long time;
        BaseColor colorLetra = new BaseColor(0, 0, 0);//new Color(245,245,245);

        validacionUtil validar = new validacionUtil();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try{


            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + ClientInstructionInternalPDF.class + " | " + origen);
            time = System.currentTimeMillis ();


            // Modo inicial el reporte
            ReportUtil reportUtil = new ReportUtil();

            //Document document = reportUtil.createDocument("Report");
            //docWriter = PdfWriter.getInstance(document, buffer);

            //document.open();
            //Paso 1 : Creación de un objeto documento
            //Document document = new Document((PageSize.LETTER).rotate(),15,15,15,30); //el atributo 'rotate' es para rotar la página y ponerla horizontal
            Document document = new Document(PageSize.LETTER, 5, 5, 5, 5);

            //Paso 2
            docWriter = PdfWriter.getInstance(document, buffer);
            // MyPageEvents event = new MyPageEvents();
            // docWriter.setPageEvent(event);

            document.addCreator("VBT Bank & Trust Online");
            document.addCreationDate();
            document.addAuthor("VBT Bank & Trust Online");

            //Paso 3: Abro el documento
            document.open();
            //////////////////////////////////////////////////////////////////////

            PdfPTable pdftable = null;
            PdfPCell cell = null;
            Phrase phrase = null;

            String numero = "";
            Random random = new Random();

            int numCartas = Integer.parseInt(carta.getMaxcartas());

            //********************** TABLAS QUE SE PINTARAN EN EL PDF ********************************//




            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";

            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            //CUADRO
            PdfContentByte cb = docWriter.getDirectContent();
            PdfTemplate template = cb.createTemplate(10, 10);
            template.setLineWidth(0.5f);
            template.rectangle(0, 0, 10f, 10f);
            template.stroke();

            Image imgCuadro = Image.getInstance(template);

            PdfTemplate template2 = cb.createTemplate(10, 10);
            template2.setLineWidth(1f);
            template2.rectangle(0, 0, 10f, 10f);
            template2.stroke();
            Image imgCuadroBold = Image.getInstance(template2);

            float ancho_tablas = 92f;


            for ( int i = 1; i <= numCartas; i++) {

                numero = numeros.get(i-1);

                //CARTA
                if (i != 1) //A partir de la segunda página se hace el salto de página
                    document.newPage();
                //DATOS DE CONTROL
                float tamaños_tabla_control[] = {2,45,28,30};

                pdftable = new PdfPTable(4);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_control);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk(""));
                //phrase = new Phrase(new Chunk("By ticking the appropriate box, please indicate whether you wish to receive a contract note for this transaction:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                cell.setColspan(2);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Control Number:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(numero,new Font(bfArial, 18, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell();
                // cell.setCellEvent(new CampoCheckBoxEvent("YES", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                //phrase = new Phrase(new Chunk("  YES, send a contract note by email.",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Client Identification No.:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ClientId",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(12,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell();
                // cell.setCellEvent(new CampoCheckBoxEvent("NO", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                //phrase = new Phrase(new Chunk("  NO, I/we do not wish to receive a contract note.",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                //cell=new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Account to be Debited:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("AccountToBeDebited",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(12,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Account Executive:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(12,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("AccountExecutive",i,30));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(12,0);
                pdftable.addCell(cell);

                document.add(pdftable);

                //DATOS DEL CLIENTE
                int tamaños_tabla_cliente[] = {25,17,16,17,5,7,13};

                pdftable = new PdfPTable(7);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_cliente);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Client Instruction for (Internal Transactions)",new Font(bfArial, TAM_LETRA_TITULO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(5,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Client Name:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ClientName",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Currency",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Address:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Address1",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("CurrencyUSD", i));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("USD",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Address2",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Address3",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("CurrencyOther", i));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Other",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("CurrencyOtherText",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Trade Date:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("TradeDate",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Settlement Date:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("SettlementDate",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                document.add(pdftable);

                //OPERACION REQUERIDA
                //int tamaños_tabla_cheque[] = {6,20,7,10,6,6,10,6,7,2,20};
                int tamaños_tabla_cheque[] = {3,23,8,12,3,6,12,3,8,2,20};

                pdftable = new PdfPTable(11);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_cheque);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk("Transaction Requested",new Font(bfArial, TAM_LETRA_SUBTITULO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(11);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(2,0);
                cell.setColspan(11);
                pdftable.addCell(cell);

                //CHEQUES
                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("CH", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Cheque Issuance",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(8);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setRowspan(7);
                pdftable.addCell(cell);

                /*TABLA PARA EL MONTO*/
                PdfPTable pdftableMontoCH = new PdfPTable(2);
                pdftableMontoCH.setWidthPercentage(90);
                pdftableMontoCH.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdftableMontoCH.setSplitRows(false);

                phrase = new Phrase(new Chunk("Amount:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftableMontoCH.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                pdftableMontoCH.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("CHAmmount",i,15));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                cell.setColspan(2);
                pdftableMontoCH.addCell(cell);
                /*FIN TABLA PARA EL MONTO*/

                cell=new PdfPCell(pdftableMontoCH);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorderWidth(1f);
                cell.setBorder(Rectangle.BOX);
                cell.setLeading(10,0);
                cell.setRowspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Drawn on:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Miami",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("DrawnOnMiami", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("New York",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("DrawnOnNewYork", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Beneficiary Name:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("BeneficiaryName",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Mailing Address",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("MailingAddress1",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("(within the United States of America)",new Font(bfArial, TAM_LETRA_NOTA, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(2);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("MailingAddress2",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Authorized Signatory(ies)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(2);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("(as stated in the signature card of the account)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("X",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(30,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("X",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(30,0);
                cell.setColspan(4);
                pdftable.addCell(cell);

                //TRANSFERENCIA INTERNA
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(5,0);
                cell.setColspan(11);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(2,0);
                cell.setColspan(11);
                pdftable.addCell(cell);

                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("IT", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Internal Transfer",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(8);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setRowspan(5);
                pdftable.addCell(cell);

                /*TABLA PARA EL MONTO*/
                PdfPTable pdftableMontoIT = new PdfPTable(2);
                pdftableMontoIT.setWidthPercentage(90);
                pdftableMontoIT.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdftableMontoIT.setSplitRows(false);

                phrase = new Phrase(new Chunk("Amount:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftableMontoIT.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                pdftableMontoIT.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ITAmmount",i,15));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                cell.setColspan(2);
                pdftableMontoIT.addCell(cell);
                /*FIN TABLA PARA EL MONTO*/

                cell=new PdfPCell(pdftableMontoIT);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorderWidth(1f);
                cell.setBorder(Rectangle.BOX);
                cell.setLeading(10,0);
                cell.setRowspan(5);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Credit Account No.:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ITCreditAccountNo",i,20));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(2);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Client Identification No.:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ITClientId",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(2);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("In the name of:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ITClientName",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Authorized Signatory(ies)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(2);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("(as stated in the signature card of the account)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("X",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(30,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("X",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(30,0);
                cell.setColspan(4);
                pdftable.addCell(cell);

                //APERTURA DE DEPOSITO A PLAZO
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(5,0);
                cell.setColspan(11);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(2,0);
                cell.setColspan(11);
                pdftable.addCell(cell);

                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("TD", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Placement of Time Deposit",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(8);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setRowspan(6);
                pdftable.addCell(cell);


                /*TABLA PARA EL MONTO*/
                PdfPTable pdftableMontoTD = new PdfPTable(2);
                pdftableMontoTD.setWidthPercentage(90);
                pdftableMontoTD.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdftableMontoTD.setSplitRows(false);

                phrase = new Phrase(new Chunk("Amount:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftableMontoTD.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                pdftableMontoTD.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("TDAmmount",i,15));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                cell.setColspan(2);
                pdftableMontoTD.addCell(cell);
                /*FIN TABLA PARA EL MONTO*/

                cell=new PdfPCell(pdftableMontoTD);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorderWidth(1f);
                cell.setBorder(Rectangle.BOX);
                cell.setLeading(10,0);
                cell.setRowspan(6);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("In the name of:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("TDClientName",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Client Identification No.",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("TDClientId",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Term:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("TDTerm",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(2);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Type: Prefered",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                phrase = new Phrase(new Chunk("(no precancellation is allowed)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell.addElement(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(4);
                pdftable.addCell(cell);
                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("TypePrefered", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Regular",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("TypeRegular", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Authorized Signatory(ies)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(2);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("(as stated in the signature card of the account)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("X",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(30,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("X",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(30,0);
                cell.setColspan(4);
                pdftable.addCell(cell);

                //FONDOS MUTUALES
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(5,0);
                cell.setColspan(11);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(5,0);
                cell.setColspan(11);
                pdftable.addCell(cell);

                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("FM", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Mutual Fund",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(8);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setRowspan(6);
                pdftable.addCell(cell);


                /*TABLA PARA EL MONTO*/
                PdfPTable pdftableMontoFM = new PdfPTable(2);
                pdftableMontoFM.setWidthPercentage(90);
                pdftableMontoFM.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdftableMontoFM.setSplitRows(false);

                phrase = new Phrase(new Chunk("Amount:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftableMontoFM.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                pdftableMontoFM.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("FMAmmount",i,15));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                cell.setColspan(2);
                pdftableMontoFM.addCell(cell);
                /*FIN TABLA PARA EL MONTO*/

                cell=new PdfPCell(pdftableMontoFM);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorderWidth(1f);
                cell.setBorder(Rectangle.BOX);
                cell.setLeading(10,0);
                cell.setRowspan(6);
                pdftable.addCell(cell);

                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("FMSubscription", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Subscription",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("FMRedemption", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Redemption",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(4);
                pdftable.addCell(cell);

                //phrase = new Phrase(new Chunk(imgCuadroBold, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("FMTransfer", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Transfer of units",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(5);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Units Quantity:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(2);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("FMUnits",i,8));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("(Please select payment option that apply)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                cell.setColspan(9);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Authorized Signatory(ies)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(2);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("(as stated in the signature card of the account)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("X",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(25,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(25,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("X",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(25,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(25,0);
                cell.setColspan(4);
                pdftable.addCell(cell);

                document.add(pdftable);

                //COMENTARIOS
                int tamaños_tabla_comentarios[] = {25,75};

                pdftable = new PdfPTable(2);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_comentarios);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(3,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.TOP);
                cell.setLeading(5,0);
                cell.setColspan(2);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Other / Comments:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Comments1",i,60));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Comments2",i,60));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                document.add(pdftable);

                //PARA USO DEL BANCO
                int tamaños_tabla_uso_banco[] = {20,10,5,20,10,5,20,10};

                pdftable = new PdfPTable(8);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_uso_banco);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(1,0);
                cell.setColspan(8);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("FOR BANK USE ONLY",new Font(bfArial, TAM_LETRA_SUBTITULO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.TOP);
                cell.setLeading(12,0);
                cell.setColspan(8);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(5,0);
                cell.setColspan(8);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Total Amount Requested:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(2);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("TotalAmountRequested",i,20));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setColspan(2);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Account Balance (available):",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(2);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("AccountBalance",i,20));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Commissions:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Commissions",i,20));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Signature Verified:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("SignatureVerified",i,20));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Approved By:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ApprovedBy",i,20));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                document.add(pdftable);
                //FIN CARTA
            }
            //Creación de bitácora

            /**
             * ********************* logger record *************************
             */
            //   Logger logger = new Logger("jdbc/vbtonlineDB");
            //logger.logAction(String username,String id_action,String id_app,String id_object,String affected_object_id,String ip,String comments)
            //   logger.logAction(strLogin, "13", "1", "11", String.valueOf(numCartas), context.getRemoteAddr(), "Interna");
            /**
             * ****************************************************************
             */
            //Paso 5: Cierra el documento
            document.close();


            inputStream  =  new ByteArrayInputStream(buffer.toByteArray());
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+ClientInstructionInternalPDF.class+" | "+origen+" | "+time);



        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            throw e;

        }
        return buffer;
    }


    private class CampoTextoEvent implements com.itextpdf.text.pdf.PdfPCellEvent {
        private String nombre;
        private int tamaño;

        public CampoTextoEvent(String nombre, int i, int tamaño) {
            this.nombre = nombre + "_" + i;
            this.tamaño = tamaño;
        }

        public void cellLayout(com.itextpdf.text.pdf.PdfPCell cell, com.itextpdf.text.Rectangle rectangle, com.itextpdf.text.pdf.PdfContentByte[] canvases) {
            //Definos las letras
            //String path = this.getClass().getResource("/vbtonline.properties").getPath();

            BaseFont bfArial=null;
            try {

                String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
                strArchivoLetraArial += "/resources/fonts/arial.ttf";

                bfArial  = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            }
            catch (Exception eFont) {
                eFont.printStackTrace();
            }
            com.itextpdf.text.pdf.PdfWriter writer = canvases[0].getPdfWriter();
            com.itextpdf.text.pdf.TextField text = new com.itextpdf.text.pdf.TextField(writer, rectangle,"text_" + nombre);
            text.setBorderWidth(0);
            text.setFont(bfArial);
            text.setFontSize(10);
            text.setText("");
            text.setMaxCharacterLength(tamaño);
            text.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

            try {
                com.itextpdf.text.pdf.PdfFormField field = text.getTextField();
                writer.addAnnotation(field);
            }
            catch(IOException ioe) {
                throw new com.itextpdf.text.ExceptionConverter(ioe);
            }
            catch(com.itextpdf.text.DocumentException de) {
                throw new com.itextpdf.text.ExceptionConverter(de);
            }
        }
    }

    private class CampoCheckBoxEvent implements com.itextpdf.text.pdf.PdfPCellEvent {
        private com.itextpdf.text.pdf.PdfFormField radiogroup;
        private String nombre;

        public CampoCheckBoxEvent(String nombre, int i) {
            this.nombre = nombre + "_" + i;
            //Grupo de CheckBox para las monedas
            try {
                radiogroup = com.itextpdf.text.pdf.PdfFormField.createEmpty(docWriter);
                radiogroup.setName("chk_" + this.nombre);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void cellLayout(com.itextpdf.text.pdf.PdfPCell cell, com.itextpdf.text.Rectangle rectangle, com.itextpdf.text.pdf.PdfContentByte[] canvases) {
            com.itextpdf.text.pdf.PdfWriter writer = canvases[0].getPdfWriter();

            float difAncho = (rectangle.getRight() - rectangle.getLeft() - 12)/2;
            float difAlto = (rectangle.getTop() - rectangle.getBottom() - 12)/2;
            com.itextpdf.text.Rectangle rect = new com.itextpdf.text.Rectangle(rectangle.getLeft() + difAncho, rectangle.getBottom() + difAlto, rectangle.getRight() - difAncho, rectangle.getTop() - difAlto);
            com.itextpdf.text.pdf.RadioCheckField check = new com.itextpdf.text.pdf.RadioCheckField(writer, rect, this.nombre, "Yes");
            check.setCheckType(com.itextpdf.text.pdf.RadioCheckField.TYPE_CHECK);
            check.setBorderColor(BaseColor.BLACK);
            check.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            radiogroup.setWidget(rect, com.itextpdf.text.pdf.PdfAnnotation.HIGHLIGHT_INVERT);
            canvases[0].getPdfWriter().addAnnotation(radiogroup);
            try {
                writer.addAnnotation(check.getCheckField());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setServletContext(ServletContext context) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }



}

package ve.com.vbtonline.vista.action.clientInstruction;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import ve.com.vbtonline.servicio.od.ClientInstructionOd;
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
public class ClientInstructionExternalPDF implements ClientInstructionPDF, Serializable {

    private static final Logger logger = Logger.getLogger(ClientInstructionExternalPDF.class);

    private InputStream inputStream;
    private PdfWriter docWriter = null;
    private final int TAM_LETRA_TITULO = 13;
    private final int TAM_LETRA_SUBTITULO = 12;
    private final int TAM_LETRA_CONTENIDO = 10;
    
    /**
     * Constructor de la clase
     */
    public ClientInstructionExternalPDF() {
    }

    public ByteArrayOutputStream generatePdf(ClientInstructionOd carta,  java.util.List<String> numeros) throws Exception {

        final String origen = "ClientInstructionExternalPDF.generatePdf";
        long time;
        BaseColor colorLetra = new BaseColor(0, 0, 0);//new Color(245,245,245);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + ClientInstructionExternalPDF.class + " | " + origen);
            time = System.currentTimeMillis ();

            // Modo inicial el reporte
            ReportUtil reportUtil = new ReportUtil();

            /* Document document = reportUtil.createDocument("Report");
            docWriter = PdfWriter.getInstance(document, buffer);

            document.open();*/
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

            int numCartas = Integer.parseInt(carta.getMaxcartas());

            //********************** TABLAS QUE SE PINTARAN EN EL PDF ********************************//

            //Definos las letras
            String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
            strArchivoLetraArial += "/resources/fonts/arial.ttf";

            BaseFont bfArial = bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);

            //CUADRO
            PdfContentByte cb = docWriter.getDirectContent();
            PdfTemplate template = cb.createTemplate(10, 10);
            template.setLineWidth(0.5f);
            template.rectangle(0, 0, 10f, 10f);
            template.stroke();

            PdfTemplate template2 = cb.createTemplate(10, 10);
            template2.setLineWidth(1f);
            template2.rectangle(0, 0, 10f, 10f);
            template2.stroke();

            float ancho_tablas = 90f;

            String numero="";

            for (int i = 1; i <= numCartas; i++) {

                numero = numeros.get(i-1);

                //CARTA
                if (i != 1) //A partir de la segunda página se hace el salto de página
                    document.newPage();
                //DATOS DE CONTROL
                float tamaños_tabla_control[] = {35,30,35};

                pdftable = new PdfPTable(3);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_control);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Control Number:"));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(numero,new Font(bfArial, 18, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Client Identification No.:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ClientId",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Account to be Debited:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("AccountToBeDebited",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Account Executive:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("AccountExecutive",i,30));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                document.add(pdftable);

                //DATOS DEL CLIENTE
                float tamaños_tabla_cliente[] = {25,17,16,17,5,7,13};

                pdftable = new PdfPTable(7);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_cliente);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk("Client Instruction for (Wire Transfer)",new Font(bfArial, TAM_LETRA_TITULO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(30,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Client Name:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ClientName",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Currency",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Address:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Address1",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                //phrase = new Phrase(new Chunk(imgCuadro, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("USD", i));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("USD",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
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
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Address3",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                //phrase = new Phrase(new Chunk(imgCuadro, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("Other", i));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Other",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("CurrencyOtherText",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Trade Date:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("TradeDate",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Settlement Date:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("SettlementDate",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                document.add(pdftable);

                //DETALLE TRANSFERENCIA
                float tamaños_tabla_transferencia[] = {25,50,2,24};

                pdftable = new PdfPTable(4);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_transferencia);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk("Wire Transfer",new Font(bfArial, TAM_LETRA_SUBTITULO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(30,0);
                cell.setColspan(4);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(4);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Beneficiary's Bank:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("BeneficiaryBank",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setRowspan(4);
                pdftable.addCell(cell);

                /*TABLA PARA EL MONTO*/
                PdfPTable pdftableMonto = new PdfPTable(2);
                pdftableMonto.setWidthPercentage(90);
                pdftableMonto.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdftableMonto.setSplitRows(false);

                phrase = new Phrase(new Chunk("Amount:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftableMonto.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                pdftableMonto.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Amount",i,15));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                cell.setColspan(2);
                pdftableMonto.addCell(cell);
                /*FIN TABLA PARA EL MONTO*/

                cell=new PdfPCell(pdftableMonto);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorderWidth(1f);
                cell.setBorder(Rectangle.BOX);
                cell.setLeading(15,0);
                cell.setRowspan(4);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Benef. Bank (Account No., Bank Id No., ABA or Swift):",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("BeneficiaryBankData",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Beneficiary Name:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("BeneficiaryName",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Beneficiary Account No.:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("BeneficiaryAccountNo",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Intermediary Bank (if appl.):",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("IntermediaryBank",i,65));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Intermediary Bank (Bank Id No., ABA or Swift):",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("IntermediaryBankData",i,65));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("For further credit to (if appl.):",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ForFutherCreditTo",i,65));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Sender Reference:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("SenderReference",i,65));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Autorized Signatory(ies)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("(as stated in the signature card of the account)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                document.add(pdftable);

                //FIRMAS
                float tamaños_tabla_firmas[] = {2,39,8,2,39};

                pdftable = new PdfPTable(5);
                pdftable.setWidths(tamaños_tabla_firmas);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk("X",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("X",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(30,0);
                pdftable.addCell(cell);

                document.add(pdftable);

                //COMENTARIOS
                float tamaños_tabla_comentarios[] = {25,75};

                pdftable = new PdfPTable(2);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_comentarios);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                cell.setColspan(2);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(2);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Other / Comments:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(20,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Comments1",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(20,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(20,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Comments2",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(20,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(20,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Comments3",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(20,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(20,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Comments4",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(20,0);
                pdftable.addCell(cell);

                document.add(pdftable);

                //PARA USO DEL BANCO
                float tamaños_tabla_uso_banco[] = {25,21,8,25,21};

                pdftable = new PdfPTable(5);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_uso_banco);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk("FOR BANK USE ONLY",new Font(bfArial, TAM_LETRA_SUBTITULO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.TOP);
                cell.setLeading(15,0);
                cell.setColspan(5);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                cell.setColspan(5);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Total Amount Requested:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("TotalAmountRequested",i,20));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
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
                phrase = new Phrase(new Chunk(" "));
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
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
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
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Approved By:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ApprovedBy2",i,20));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                document.add(pdftable);

            }
            //Creación de bitácora
            /**
             * ********************* logger record *************************
             */
         //   Logger logger = new Logger("jdbc/vbtonlineDB");
            //logger.logAction(String username,String id_action,String id_app,String id_object,String affected_object_id,String ip,String comments)
           // logger.logAction(strLogin, "13", "1", "11", String.valueOf(numCartas), context.getRemoteAddr(), "Interna");
            /**
             * ****************************************************************
             */
            //Paso 5: Cierra el documento
            document.close();

            inputStream  =  new ByteArrayInputStream(buffer.toByteArray());
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+ClientInstructionExternalPDF.class+" | "+origen+" | "+time);

        } catch (Exception e){
                logger.error(e);
                throw e;

        }
        return buffer;
    }

   private class CampoTextoEvent implements PdfPCellEvent {

        private String nombre;
        private int tamaño;

        public CampoTextoEvent(String nombre, int i, int tamaño) {
            this.nombre = nombre + "_" + i;
            this.tamaño = tamaño;
        }

        public void cellLayout(PdfPCell cell, Rectangle rectangle, PdfContentByte[] canvases) {
            //Definos las letras
            BaseFont font = null;

            try {
                String strArchivoLetraArial = ServletActionContext.getServletContext().getRealPath("/");
                strArchivoLetraArial += "/resources/fonts/arial.ttf";

                font = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
            }
            catch (Exception eFont) {
                eFont.printStackTrace();
            }
            PdfWriter writer = canvases[0].getPdfWriter();
            TextField text = new TextField(writer, rectangle,"text_" + nombre);
            text.setBorderWidth(0);
            text.setFont(font);
            text.setFontSize(10);
            text.setText("");
            text.setMaxCharacterLength(tamaño);
            text.setAlignment(Element.ALIGN_CENTER);

            try {
                PdfFormField field = text.getTextField();
                writer.addAnnotation(field);
            }
            catch(IOException ioe) {
                throw new ExceptionConverter(ioe);
            }
            catch(DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }
    }

    private class CampoCheckBoxEvent implements PdfPCellEvent {
        private PdfFormField radiogroup;
        private String nombre;

        public CampoCheckBoxEvent(String nombre, int i) {
            this.nombre = nombre + "_" + i;
            //Grupo de CheckBox para las monedas
            try {
                radiogroup = PdfFormField.createEmpty(docWriter);
                radiogroup.setName("chk_" + this.nombre);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void cellLayout(PdfPCell cell, Rectangle rectangle, PdfContentByte[] canvases) {
            com.itextpdf.text.pdf.PdfWriter writer = canvases[0].getPdfWriter();

            float difAncho = (rectangle.getRight() - rectangle.getLeft() - 12)/2;
            float difAlto = (rectangle.getTop() - rectangle.getBottom() - 12)/2;
            Rectangle rect = new Rectangle(rectangle.getLeft() + difAncho, rectangle.getBottom() + difAlto, rectangle.getRight() - difAncho, rectangle.getTop() - difAlto);
            RadioCheckField check = new RadioCheckField(writer, rect, this.nombre, "Yes");
            check.setCheckType(RadioCheckField.TYPE_CHECK);
            check.setBorderColor(BaseColor.BLACK);
            check.setAlignment(Element.ALIGN_CENTER);
            radiogroup.setWidget(rect, PdfAnnotation.HIGHLIGHT_INVERT);
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
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}

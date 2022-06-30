package ve.com.vbtonline.vista.action.clientInstruction;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.TextField;
import com.itextpdf.text.List;

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
    private final int TAM_LETRA_NOTA = 8;

    /**
     * Constructor de la clase
     */
    public ClientInstructionExternalPDF() {
    }

    /*public ByteArrayOutputStream generatePdf(ClientInstructionOd carta, List<String> numeros) throws Exception{
     return null;
    } */

    public ByteArrayOutputStream generatePdf(ClientInstructionOd carta,  java.util.List<String> numeros) throws Exception {

        final String origen = "ClientInstructionExternalPDF.generatePdf";
        long time;
        BaseColor colorLetra = new BaseColor(0, 0, 0);//new Color(245,245,245);
        BaseColor colorLetraBI = new BaseColor(100, 100, 100);//Color Gris Oscuro

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
            template2.setLineWidth(0.5f);
            template2.rectangle(0, 0, 10f, 10f);
            template2.stroke();

            float ancho_tablas = 96f;

            String numero="";

            for (int i = 1; i <= numCartas; i++) {

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

                //phrase = new Phrase(new Chunk("By ticking the appropriate box, please indicate whether you wish to receive a contract note for this transaction:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                cell.setColspan(2);
                pdftable.addCell(cell);


                phrase = new Phrase(new Chunk("Control Number:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(  numero,new Font(bfArial, 18, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell();
                // cell.setCellEvent(new CampoCheckBoxEvent("YES", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                //phrase = new Phrase(new Chunk("  YES, send a contract note by email.",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Client Identification No.:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ClientId",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell();
                //cell.setCellEvent(new CampoCheckBoxEvent("NO", i));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                //phrase = new Phrase(new Chunk("  NO, I/we do not wish to receive a contract note.",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                //cell=new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);


                phrase = new Phrase(new Chunk("Account to be Debited:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);

                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("AccountToBeDebited",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(10,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);


                phrase = new Phrase(new Chunk("Account Executive:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("AccountExecutive",i,30));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(10,0);
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
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ClientName",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Currency",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Address:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Address1",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                //phrase = new Phrase(new Chunk(imgCuadro, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("USD", i));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("USD",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Address2",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Address3",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);
                //phrase = new Phrase(new Chunk(imgCuadro, 1f, 1f));
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setCellEvent(new CampoCheckBoxEvent("Other", i));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Other",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("CurrencyOtherText",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Trade Date:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("TradeDate",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("Settlement Date:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("SettlementDate",i,10));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                cell.setColspan(3);

                pdftable.addCell(cell);

                document.add(pdftable);

                //DETALLE TRANSFERENCIA
                float tamaños_tabla_transferencia[] = {26,21,8,21,1,12,12};

                pdftable = new PdfPTable(7);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_transferencia);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk("Wire Transfer",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(20,0);
                cell.setColspan(7);
                pdftable.addCell(cell);

                /*phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                cell.setColspan(7);
                pdftable.addCell(cell);  */

                phrase = new Phrase(new Chunk("Beneficiary's Bank:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();

                cell.setCellEvent(new CampoTextoEvent("BeneficiaryBank",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
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
                //cell.setLeading(15,0);
                pdftableMonto.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                pdftableMonto.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Ammount",i,15));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(30,0);
                cell.setColspan(2);
                pdftableMonto.addCell(cell);
                /*FIN TABLA PARA EL MONTO*/

                cell=new PdfPCell(pdftableMonto);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBorderWidth(1f);
                cell.setBorder(Rectangle.BOX);
                //cell.setLeading(15,0);
                cell.setRowspan(4);
                cell.setColspan(2);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Benef. Bank (Account No., Bank Id No., ABA or Swift):",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(10,0);
                pdftable.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("BeneficiaryBankData",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(10,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Beneficiary Name:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("BeneficiaryName",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);


                phrase = new Phrase(new Chunk("Beneficiary Account No.:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("BeneficiaryAccountNo",i,49));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(3);
                pdftable.addCell(cell);

                /*****/
                phrase = new Phrase(new Chunk("Beneficiary Address:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("BeneficiaryAddress",i,74));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(6);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("City:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("City",i,21));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Country:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Country",i,21));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(""));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Postal Code:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("PostalCode",i,12));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                /**** */

                phrase = new Phrase(new Chunk("Intermediary Bank (if appl.):",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("IntermediaryBank",i,65));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(6);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Intermediary Bank (Bank Id No., ABA or Swift):",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(10,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("IntermediaryBankData",i,65));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(10,0);
                cell.setColspan(6);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("For further credit to (if appl.):",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("ForFutherCreditTo",i,65));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(6);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Sender Reference:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("SenderReference",i,65));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(6);
                pdftable.addCell(cell);


                phrase = new Phrase(new Chunk("Authorized Signatory(ies)",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
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
                //cell.setLeading(15,0);
                cell.setColspan(6);
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
                cell.setLeading(25,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
               // cell.setLeading(30,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk("X",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(25,0);
                pdftable.addCell(cell);
                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(30,0);
                pdftable.addCell(cell);

                document.add(pdftable);

                //MOTIVOS
                float tamaños_tabla_motivos[] = {25, 40, 5, 30};

                pdftable = new PdfPTable(4);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_motivos);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk(" "));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                cell.setColspan(4);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                cell.setColspan(4);
                pdftable.addCell(cell);



                phrase = new Phrase(new Chunk("(*)  Purpose / Reason:", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10, 0);
                pdftable.addCell(cell);

                cell = new PdfPCell();
                cell.setCellEvent(new SelectCellEvent("motivo", i, carta));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                cell.setLeading(10, 0);
                pdftable.addCell(cell);


                /*cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setCellEvent(new AnnotationEvent("Purpose / Reason", "Si el motivo elegido es Others debe ingresar una descripcion en el campo siguiente"));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(10, 0);
                pdftable.addCell(cell);  */

                cell = new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("motivo_other", i, 35));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(10, 0);
                pdftable.addCell(cell);

                document.add(pdftable);

                //COMENTARIOS
                float tamaños_tabla_comentarios[] = {25,75};

                pdftable = new PdfPTable(2);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_comentarios);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                /*phrase = new Phrase(new Chunk(" "));
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
                pdftable.addCell(cell);  */

                phrase = new Phrase(new Chunk("Other / Comments:",new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setLeading(15,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Comments1",i,80));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(20,0);
                pdftable.addCell(cell);
                cell=new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("Comments2",i,80));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(20,0);
                pdftable.addCell(cell);

                /*phrase = new Phrase(new Chunk(" "));
                cell=new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(20,0);
                pdftable.addCell(cell);
                /*cell=new PdfPCell();
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
                pdftable.addCell(cell);*/

                document.add(pdftable);


                float tamaños_tabla_beneficiary[] = {55, 45};

                pdftable = new PdfPTable(2);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_beneficiary);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk(" "));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                cell.setColspan(2);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("BENEFICIARY INFORMATION", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("(*) Mandatory Fields", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                pdftable.addCell(cell);

                document.add(pdftable);

                float tamaños_tabla_individual[] = {13, 35, 28, 24};

                pdftable = new PdfPTable(4);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_individual);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                /*phrase = new Phrase(new Chunk(" "));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(25, 0);
                cell.setColspan(4);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("BENEFICIARY INFORMATION", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("(*) Mandatory Fields", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                pdftable.addCell(cell); */


                phrase = new Phrase(new Chunk("For Individuals:", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                pdftable.addCell(cell);



                phrase = new Phrase(new Chunk("(*) Full Name:", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(10, 0);
                pdftable.addCell(cell);

                cell = new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("FullNameI", i, 35, true));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(10, 0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("(*) Date of Birth (dd/mm/yyyy):", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(10, 0);
                pdftable.addCell(cell);

                cell = new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("DateBI", i, 10, true));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(10, 0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("(*) Nationality:", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                cell = new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("NationalityI", i, 35, true));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("(*) I.D. / Passport Nr.:", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                cell = new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("IdPassportI", i, 20, true));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(15,0);
                pdftable.addCell(cell);

                document.add(pdftable);

                //COMPA�IA BENEFICIARIA
                float tamaños_tabla_companie[] = {13, 32, 25, 35};

                pdftable = new PdfPTable(4);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_companie);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                //Linea de Companies
                /*phrase = new Phrase(new Chunk(" "));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBorderWidth(1);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(25, 0);
                cell.setColspan(4);
                pdftable.addCell(cell); */

                phrase = new Phrase(new Chunk(" "));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("For Companies:", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                pdftable.addCell(cell);

                /*phrase = new Phrase(new Chunk("COMPANIES BENEFICIARY INFORMATION", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.BOLD, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("(*) Mandatory Fields", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(4);
                pdftable.addCell(cell); */

                phrase = new Phrase(new Chunk("(*) Full Name:", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(10, 0);
                pdftable.addCell(cell);

                cell = new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("FullNameC", i, 35, true));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(10, 0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("(*) Country of Incorporation:", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(10, 0);
                pdftable.addCell(cell);

                cell = new PdfPCell();
                cell.setCellEvent(new CampoTextoEvent("CountryIncor", i, 25, true));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.BOTTOM);
                //cell.setLeading(10, 0);
                pdftable.addCell(cell);

                document.add(pdftable);

                //NOTAS
                float tamaños_tabla_notas[] = {100};

                pdftable = new PdfPTable(1);
                pdftable.setWidthPercentage(ancho_tablas);
                pdftable.setWidths(tamaños_tabla_notas);
                pdftable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdftable.setSplitRows(false);

                phrase = new Phrase(new Chunk(" "));
                cell = new PdfPCell(phrase);
                cell.setBorder(Rectangle.NO_BORDER);
                //cell.setLeading(15, 0);
                pdftable.addCell(cell);

                phrase = new Phrase(new Chunk("Notes:", new Font(bfArial, TAM_LETRA_CONTENIDO, Font.NORMAL, colorLetra)));
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                pdftable.addCell(cell);

                List list = new List();
                list.setIndentationLeft(23);

                list.setListSymbol("\u2022");

                ListItem item1= new ListItem("Please be advised that recent implementations by the United States of America of sanctions in relation to certain jurisdictions and individuals, as well as further measures being imposed for correspondent banks, have necessitated " +
                        "additional internal procedures.  As such delays may be encountered when executing requests, and, in some instance, it may not be possible to execute the request. We take this opportunity to reaffirm our commitment to " +
                        "ensuring that we make the greatest efforts in order to efficiently carry out requests and thank you for your understanding.", new Font(bfArial, TAM_LETRA_NOTA, Font.NORMAL, colorLetra));
                item1.setAlignment(Element.ALIGN_JUSTIFIED);
                list.add(item1);

                ListItem item2 = new ListItem("I declare, under oath, that the information provided in this form is, to the best of my knowledge and belief true, accurate and reflects the knowledge I possess of the requested transaction.\n" +
                                      "I hereby consent and authorize the institution to validate its accuracy and affirm that the amounts of money or funds that I send or receive comes from lawful activities and are not associated with processes or procedures that could " +
                                      "link them to the money laundering or financing of terrorist activities or proliferation of weapons of mass destruction or cybercrime.", new Font(bfArial, TAM_LETRA_NOTA, Font.NORMAL, colorLetra));
                item2.setAlignment(Element.ALIGN_JUSTIFIED);
                list.add(item2);

                phrase = new Phrase();
                phrase.add(list);
                cell = new PdfPCell(phrase);
                cell.addElement(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                pdftable.addCell(cell);

                document.add(pdftable);

                //PARA USO DEL BANCO
                /*float tamaños_tabla_uso_banco[] = {25,21,8,25,21};

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

                document.add(pdftable); */

            }
            //Creación de bitácora
            /**
             * ********************* logger record *************************
             */
              // Logger logger = new Logger("jdbc/vbtonlineDB");
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
            e.printStackTrace();
            logger.error(e);
            throw e;

        }
        return buffer;
    }

    private class CampoTextoEvent implements PdfPCellEvent {

        private String nombre;
        private int tamaño;
        private Boolean required;

        public CampoTextoEvent(String nombre, int i, int tamaño, Boolean required) {
            this.nombre = nombre + "_" + i;
            this.tamaño = tamaño;
            this.required = required;
        }

        public CampoTextoEvent(String nombre, int i, int tamaño) {
            this.nombre = nombre + "_" + i;
            this.tamaño = tamaño;
            this.required = false;
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

            if (required) {
                text.setOptions(TextField.EDIT | TextField.REQUIRED);
            }

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

    class SelectCellEvent implements PdfPCellEvent {

        protected PdfFormField selectGroup;
        protected String name;
        protected String[] exports;
        protected String[] options;

        public SelectCellEvent(String name, int i, ClientInstructionOd carta) throws DocumentException, IOException {
            this.selectGroup = PdfFormField.createEmpty(docWriter);
            this.name = name + "_" + i;
            this.selectGroup.setFieldName(this.name);
            try {
                exports = new String[carta.getMotivos().size() + 1];
                options = new String[carta.getMotivos().size() + 1];

                exports[0] = "options";
                options[0] = "Select an option";

                for (int j = 0; j < carta.getMotivos().size(); j++) {
                    exports[j + 1] = carta.getMotivos().get(j).getValue();
                    options[j + 1] = carta.getMotivos().get(j).getLabel();
                    if(carta.getMotivos().get(j).getLabel().equals("Other"))
                        options[j + 1].concat(" Complete the next text field");
                }
            } catch (Exception p) {
                p.printStackTrace();
            }
        }

        public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
            PdfWriter writer = canvases[0].getPdfWriter();
            TextField tf = new TextField(writer, position, name);
            tf.setBorderStyle(PdfBorderDictionary.STYLE_BEVELED);
            tf.setVisibility(TextField.VISIBLE);
            tf.setBorderColor(BaseColor.RED);
            tf.setChoiceExports(exports);
            tf.setChoices(options);
            tf.setDefaultText("Select an option");
            tf.setAlignment(Element.ALIGN_LEFT);
            tf.setFontSize(8);

            try {
                selectGroup.addKid(tf.getComboField());
                writer.addAnnotation(selectGroup);
            } catch (Exception e) {
                throw new ExceptionConverter(e);
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

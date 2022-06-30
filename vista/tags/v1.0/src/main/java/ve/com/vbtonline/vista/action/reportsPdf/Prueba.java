package ve.com.vbtonline.vista.action.reportsPdf;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.venezolano.util.text.NullFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//@author hug0

public class Prueba {
    //Ruta del archivo, esto es dentro del proyecto Netbeans
    public static String archivo="D://PdfPagXdeY.pdf";

    //Clase para manejar los Header y los Footer
    //Toma los metodos de PdfPageEventHelper
    class HeaderFooter extends PdfPageEventHelper {
        //Template para el número total de páginas
        PdfTemplate total;

        //Evento cuando que se ejecuta al comenzar una documento
        @Override
        public void onOpenDocument(PdfWriter writer, Document document){
            //Definimos las medidas del template
            total = writer.getDirectContent().createTemplate(10, 16);
        }

        //Evento cuando que se ejecuta al terminar una página
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            String mes="3";
            String anio="2013";
            try {
                String msgPiedePagina1 = "In accordance with the applicable laws of the Cayman Islands and pursuant to a special resolution of the sole shareholder of Venecredit Bank & Trust, Ltd. (the \"Bank\") passed on ";
                String msgPiedePagina2 = "24 February 2012, the name of the Bank changed from \"Venecredit Bank & Trust, Ltd.\" to \"VBT Bank & Trust, Ltd.\"  All terms and conditions relating to the account agreements ";
                String msgPiedePagina3 = "between the Bank and its account holders remain unchanged.";

                String Mensline1="PLEASE REVIEW AND REPORT ANY DISCREPANCIES TO OUR AUDITORS DELOITTE & TOUCHE AT ONE CAPITAL PLAZA, P.O. BOX 1787, GRAND CAYMAN";
                String Mensline2="KY1-1109, CAYMAN ISLANDS. TO THE ATTENTION OF STACEY MATHIS EMAIL: smathis@deloitte.com TEL NUMBER: (345) 814-2277 AND ";
                String Mensline3="FAX NUMBER: (345) 949-8238.";

                String PiedePagina1 = "VBT Bank & Trust, Ltd.";
                String PiedePagina2 = "P.O.Box 454, 4th Floor, Flagship Building, 70 Harbour Drive, George Town, Grand Cayman KY1-1106, CAYMAN ISLANDS";
                String PiedePagina3 = "Phone (345)949-6917. Fax (345)949-8017. e-mail: vib@candw.ky Website: www.vbtbank.com";

                PdfPTable TablePie_pag = new PdfPTable(1);
                PdfPTable Pg = new PdfPTable(2);

                TablePie_pag.setTotalWidth(550);
                TablePie_pag.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                TablePie_pag.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                Pg.setTotalWidth(100);
                Pg.setWidths(new int[]{90, 10});
                Pg.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                Pg.getDefaultCell().setBorder(Rectangle.ALIGN_TOP);
                ReportUtil reportUtil = new ReportUtil();
                PdfPCell  celldatos;

                if ( (mes.equals("3") || mes.equals("4") || mes.equals("5")) && "2012".equals(anio) ) {

                    celldatos= new PdfPCell(reportUtil.formatoValor(7,msgPiedePagina1));
                    celldatos.setBorder(Rectangle.TOP);
                    celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                    TablePie_pag.addCell(celldatos);

                    celldatos= new PdfPCell(reportUtil.formatoValor(7,msgPiedePagina2));
                    celldatos.setBorder(Rectangle.NO_BORDER);
                    celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                    TablePie_pag.addCell(celldatos);

                    celldatos= new PdfPCell(reportUtil.formatoValor(7,msgPiedePagina3));
                    celldatos.setBorder(Rectangle.NO_BORDER);
                    celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                    TablePie_pag.addCell(celldatos);
                }

                int intMes = 0;
                if (!NullFormatter.isBlank(mes)) {
                    intMes = Integer.parseInt(mes);
                }

                if (intMes >= 9) {
                    celldatos= new PdfPCell(reportUtil.formatoValor(7,Mensline1));
                    celldatos.setBorder(Rectangle.TOP);
                    celldatos.setColspan(2);
                    celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                    TablePie_pag.addCell(celldatos);

                    celldatos= new PdfPCell(reportUtil.formatoValor(7,Mensline2));
                    celldatos.setBorder(Rectangle.NO_BORDER);
                    celldatos.setColspan(2);
                    celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                    TablePie_pag.addCell(celldatos);

                    celldatos= new PdfPCell(reportUtil.formatoValor(7,Mensline3));
                    celldatos.setBorder(Rectangle.NO_BORDER);
                    celldatos.setColspan(2);
                    celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                    TablePie_pag.addCell(celldatos);
                }

                celldatos= new PdfPCell(reportUtil.formatoValor(7,PiedePagina1));
                celldatos.setBorder(Rectangle.TOP);
                celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                TablePie_pag.addCell(celldatos);

                celldatos= new PdfPCell(reportUtil.formatoValor(7,PiedePagina2));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                TablePie_pag.addCell(celldatos);

                celldatos= new PdfPCell(reportUtil.formatoValor(7,PiedePagina3));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                TablePie_pag.addCell(celldatos);


                celldatos= new PdfPCell(reportUtil.formatoValor(7,String.format("Page %d of ", writer.getCurrentPageNumber())));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
                celldatos.setVerticalAlignment(Rectangle.ALIGN_BOTTOM);
                Pg.addCell(celldatos);

                celldatos = new PdfPCell(Image.getInstance(total));
                celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                celldatos.setBorder(Rectangle.NO_BORDER);
                Pg.addCell(celldatos);

                TablePie_pag.addCell(Pg);
                TablePie_pag.writeSelectedRows(0, -1, 40, 70, writer.getDirectContent());
            } catch (BadElementException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (DocumentException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            //Escribimos el contenido al cerrar el documento
            Phrase frase =new Phrase();
            frase =new Phrase();
            frase.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.BOLD, BaseColor.BLACK));
            frase.add(new Chunk(String.valueOf(writer.getPageNumber() - 1)));

            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,frase,2, 2, 0);
        }
    }


    public void createPdf(){
        try{
      /*Declaramos documento como un objeto Document
       *Asignamos el tamaño de hoja y los margenes
       */
            Document document = new Document(PageSize.LETTER, 80, 80, 75, 75);

            //writer es declarado como el método utilizado para escribir en el archivo
            //Obtenemos la instancia del archivo a utilizar
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(archivo)));

            //Declaramos una instancia de los eventos en HeaderFooter
            HeaderFooter event = new HeaderFooter();
            writer.setPageEvent(event);

            //Escribimos un titulo del pdf
            document.addTitle("Titulo del PDF");

            //Escribimos un autor
            document.addAuthor("hug0");

            //Abrimos el documento
            document.open();

            //Declaramos un texto como Paragraph
            //Le podemos dar formado como alineación, tamaño y color a la fuente.
            Paragraph paragraph= new Paragraph();
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setFont(new Font(FontFactory.getFont("Sans",20,Font.BOLD, BaseColor.BLUE)));
            paragraph.add("Garabatos Linux");

            //Agregamos el parrafo al documento
            document.add(paragraph);

            //Agregamos una pagina
            document.newPage();
            //Añadimos texto
            document.add(new Paragraph("Segunda página"));

            //Añadimos una nueva pagina
            document.newPage();
            //Agregamos texto
            document.add(new Paragraph("Tercera página"));

            //Cerramos el documento y la escritura
            document.close();
            writer.close();

        }catch(FileNotFoundException | DocumentException e){
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        //Creamos un nuevo pdf
        new Prueba().createPdf();
    }
}
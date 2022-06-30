//package ve.com.vbtonline.vista.action.accounts;
//
//import com.venezolano.util.*;
//import com.venezolano.util.logger.*;
//import com.venezolano.util.text.*;
//import com.venezolano.webutil.*;
//import java.io.*;
//import java.util.*;
//import java.awt.Color;
//
//// import the iText packages
//import com.lowagie.text.*;
//import com.lowagie.text.pdf.*;
//
//public class reportePdf implements ReportHandler {
//    private String        page;
//    private ClientContext context;
//    private PageConfig    pageConfig;
//    private String        strSaldoMesAnterior;
//
//    private Color backcolor          = new Color(192,192,192);//new Color(245,245,245);
//    private int   sizeLetraTitulo    = 16;//19;
//    private int   sizeLetraSubtitulo = 12;//14;
//    private int   sizeLetraContenido = 9;//9;
//    private int   sizeLetraMensaje   = 8;//8;
//    private int   sizeLetraDireccion = 7;//7;
//
//    private String msgPiedePagina1 = "In accordance with the applicable laws of the Cayman Islands and pursuant to a special resolution of the sole shareholder of Venecredit Bank & Trust, Ltd. (the \"Bank\") passed on ";
//    private String msgPiedePagina2 = "24 February 2012, the name of the Bank changed from \"Venecredit Bank & Trust, Ltd.\" to \"VBT Bank & Trust, Ltd.\"  All terms and conditions relating to the account agreements ";
//    private String msgPiedePagina3 = "between the Bank and its account holders remain unchanged.";
//
//    public reportePdf() {}
//
//    public ByteArrayOutputStream getReport(ClientContext context) {
//
//        String language    = (String)context.getSessionObject("language");
//        String locale      = (String)context.getSessionObject("locale");
//
//        String newlanguage = context.getParameter("nl");
//        String newlocale   = context.getParameter("nlo");
//
//        if( (!NullFormatter.isBlank(newlanguage)) && (!NullFormatter.isBlank(newlocale)) ) {
//            language = newlanguage;
//            locale   = newlocale;
//            context.putSessionObject("language",language);
//            context.putSessionObject("locale",locale);
//        } else if( (NullFormatter.isBlank(language)) && (NullFormatter.isBlank(locale)) ) {
//            locale   = "us";
//            language = "en";
//            context.putSessionObject("language",language);
//            context.putSessionObject("locale",locale);
//        }
//
//        PageConfig pageConfig = new PageConfig("PDFCuentasEdoCuenta_"+locale+"_"+language,"vbtonline",locale,language);
//
//        String strNumeroCuenta  = NullFormatter.formatBlank(context.getParameter("numeroCuenta"));
//        String strCodigoCartera = NullFormatter.formatBlank(context.getParameter("codigoCartera"));
//        String strCmbMes        = NullFormatter.formatBlank(context.getParameter("cmbMes"));
//        String strTxtAño        = NullFormatter.formatBlank(context.getParameter("txtAño"));
//        String strLogin         = NullFormatter.formatBlank((String)context.getSessionObject("login"));
//
//        if (!NullFormatter.isBlank(strNumeroCuenta) && !NullFormatter.isBlank(strLogin)) {
//            /***********************  logger record **************************/
//            Logger logger = new Logger("jdbc/vbtonlineDB");
//            //logger.logAction(String username,String id_action,String id_app,String id_object,String affected_object_id,String ip,String comments)
//            logger.logAction(strLogin,"13","1","4",strNumeroCuenta,context.getRemoteAddr(),"Edo. Cuenta Fecha:" + strCmbMes + "/" + strTxtAño + " Cartera:" + strCodigoCartera);
//            /*******************************************************************/
//        }
//
//        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
//
//        try {
//            // step 1: creation of a document-object
//            Document document = new Document(PageSize.LETTER,20,20,35,40);
//
//            // step 2:
//            PdfWriter docWriter = PdfWriter.getInstance(document, baosPDF);
//            MyPageEvents event = new MyPageEvents();
//            docWriter.setPageEvent(event);
//
//            document.addCreator("VBT Bank & Trust Online");
//            document.addCreationDate();
//            document.addAuthor("VBT Bank & Trust Online");
//
//            // step 3: we open the document
//            document.open();
//            /***********************************************************************************************
//             *                      Detalle del estado de cuenta                                            *
//             ************************************************************************************************/
//            String detalleSQL = "   SELECT to_char(DET.MOVFECHOPE,'DD/MM/YYYY') FECHAOPERACION, "  // 0
//                    + "          to_char(DET.MOVFECHVAL,'DD/MM/YYYY') FECHAVALOR, "      // 1
//                    + "          DET.DESCMOV, "                                          // 2
//                    + "          DET.REFBANMOV REFERENCIA, "                             // 3
//                    + "          DET.MTOMOV "                                            // 4
//                    + "     FROM BANCO_VBT.EDO_DETALLE DET "
//                    + "    WHERE DET.CODEMP  = '0000009539' "
//                    + "      AND DET.CODCOL  = '" + strNumeroCuenta + "'"
//                    + "      AND DET.CODINST = 'CAH' "
//                    + "      AND to_char(DET.MOVFECHOPE,'MMYYYY') = LPAD('" + strCmbMes + strTxtAño + "', 6, '0') "
//                    + " ORDER BY DET.MOVFECHOPE,DET.MOVFECHVAL,DET.DESCMOV ASC, DET.MTOMOV DESC";
//
////        System.out.println("PDFdetalleSQL: " + detalleSQL);
//
//            com.venezolano.util.database.Table tableDetalle = new com.venezolano.util.database.Table();
//            com.venezolano.util.database.Row   row          = new com.venezolano.util.database.Row();
//            tableDetalle.fillWithQuery(detalleSQL,"jdbc/sanluisDB");
//            int max = tableDetalle.numberOfRows();
//
//            Cell   cell               = null;
//            double saldo              = 0.0;
//            double montoMovimiento    = 0.0;
//            String primeraDescripcion = "";
//            String primerSaldo        = "";
//
//            int headerwidths[] = {12,12,25,12,12,12,15};
//            com.lowagie.text.Table pdftable = new com.lowagie.text.Table(7);
//
//            //Definos las letras
//            String strArchivoLetraArial = this.getClass().getResource("/vbtonline.properties").getPath();
//            strArchivoLetraArial = strArchivoLetraArial.substring(0,strArchivoLetraArial.length() - (new String("WEB-INF/classes/vbtonline.properties")).length());
//            strArchivoLetraArial += "fonts/arial.ttf";
//
//            BaseFont bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
//
//            //-------------------------------------------------
//            if (max == 0) {         // Si no existen movimientos o transacciones para un mes y un año dado.
//                pdftable = new com.lowagie.text.Table(7);
//                pdftable.setPadding(2);
//                pdftable.setSpacing(0);
//                pdftable.setWidth(100f);
//                pdftable.setBorderWidth(0);
//                pdftable.setWidths(headerwidths);
//
//                cell = new Cell("");
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                cell.setColspan(2);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("balancebrought"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell("");
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                cell.setColspan(3);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(CurrencyFormatter.formatNumber(strSaldoMesAnterior,2,","),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//
//                pdftable.addCell(cell);
//                document.add(pdftable);
//            }
//
//            //-------------------------- Movimientos del estado de cuenta ------------------------------------
//            for (int i=0;i<max;i++) {
//                // Primera linea del detalle de los movimientos del estado de cuenta
//                if ( (i%34) == 0 ) {   //  Se muestran 34 movimientos o transacciones por página
//                    if ((i!=0) && ((i%34) == 0) ) {document.newPage();}
//                    if(i == 0) {
//                        primeraDescripcion = pageConfig.getFieldLabel("balancebrought");
//                        primerSaldo        = CurrencyFormatter.formatNumber(strSaldoMesAnterior,2,",");
//                        saldo = Double.parseDouble(strSaldoMesAnterior);
//                    } else {
//                        primeraDescripcion = pageConfig.getFieldLabel("lastpagebalance");
//                        primerSaldo        = CurrencyFormatter.formatNumber(Double.toString(saldo),2,",");
//                    }
//                    pdftable = new com.lowagie.text.Table(7);
//                    pdftable.setPadding(2);
//                    pdftable.setSpacing(0);
//                    pdftable.setWidth(100f);
//                    pdftable.setBorderWidth(0);
//                    pdftable.setWidths(headerwidths);
//
//                    cell = new Cell("");
//                    cell.setBorder(Rectangle.NO_BORDER);
//                    cell.setLeading(5);
//                    cell.setColspan(2);
//                    pdftable.addCell(cell);
//
//                    cell = new Cell(new Chunk(primeraDescripcion,new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                    cell.setBorder(Rectangle.NO_BORDER);
//                    cell.setLeading(5);
//                    pdftable.addCell(cell);
//
//                    cell = new Cell("");
//                    cell.setBorder(Rectangle.NO_BORDER);
//                    cell.setLeading(5);
//                    cell.setColspan(3);
//                    pdftable.addCell(cell);
//
//                    cell = new Cell(new Chunk(primerSaldo,new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(Rectangle.NO_BORDER);
//                    cell.setLeading(5);
//
//                    pdftable.addCell(cell);
//                    document.add(pdftable);
//                }
//                //------------------- Resto de las lineas de los movimientos del estado de cuenta ---------------------------
//                pdftable = new com.lowagie.text.Table(7);
//                pdftable.setPadding(2);
//                pdftable.setSpacing(0);
//                pdftable.setWidth(100f);
//                pdftable.setBorderWidth(0);
//                pdftable.setWidths(headerwidths);
//
//                row = tableDetalle.getRowAt(i);
//
//                cell = new Cell(new Chunk(NullFormatter.formatHtmlBlank(row.getColumnAt(0)),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(NullFormatter.formatHtmlBlank(row.getColumnAt(1)),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(NullFormatter.formatHtmlBlank(row.getColumnAt(2)),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(row.getColumnAt(3),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                pdftable.addCell(cell);
//
//                montoMovimiento = Double.parseDouble(NullFormatter.formatHtmlBlank(row.getColumnAt(4)));
//                saldo = (saldo + montoMovimiento);
//
//                cell = new Cell(new Chunk(montoMovimiento > 0 ? "":""+CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(montoMovimiento)),2,","),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(montoMovimiento > 0 ? ""+CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(montoMovimiento)),2,","):"",new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(CurrencyFormatter.formatNumber(Double.toString(saldo),2,","),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                document.add(pdftable);
//
//            }
//            // **************************
//            // Mensajes de información al cliente, pueden ser temporales
//            // *****************************************************************
//            PdfContentByte cb;
//            cb = docWriter.getDirectContent();
//
//            // Grueso de la linea
//            cb.setLineWidth(1f);
//            // Coordenadas de la linea
//            cb.moveTo(20, 100);
//            cb.lineTo(594, 100);
//            // Pintar linea
//            cb.stroke();
//
//            if ( (strCmbMes.equals("3") || strCmbMes.equals("4") || strCmbMes.equals("5")) && "2012".equals(strTxtAño) ) {
//                cb.beginText();
//                cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 8);
//                cb.setTextMatrix(20, 90);
//                cb.showText(msgPiedePagina1);
//
//                cb.newlineText();
//                cb.setTextMatrix(20, 80);
//                cb.showText(msgPiedePagina2);
//
//                cb.newlineText();
//                cb.setTextMatrix(20, 70);
//                cb.showText(msgPiedePagina3);
//
//                cb.endText();
//            }
//
//            int intMes = 0;
//            if (!NullFormatter.isBlank(strCmbMes)) {
//                intMes = Integer.parseInt(strCmbMes);
//            }
//
//            if (intMes >= 9) {
//                cb.beginText();
//                cb.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 8);
//                cb.setTextMatrix(20, 90);
//                cb.showText(pageConfig.getTagFieldValue("Mensline1"));
//
//                cb.newlineText();
//                cb.setTextMatrix(20, 80);
//                cb.showText(pageConfig.getTagFieldValue("Mensline2"));
//
//                cb.newlineText();
//                cb.setTextMatrix(20, 70);
//                cb.showText(pageConfig.getTagFieldValue("Mensline3"));
//
//                cb.endText();
//            }
//            /************************ Fin Detalle del estado de cuenta ***********************************/
//
//            // step 5: we close the document
//            document.close();
//        } catch(DocumentException de) {
//            page="/vbtonline/pages/Error.jsp?pagina=reportePdf.java";
//            context.putSessionObject("excepcion",de);
//
//            Enumeration enumeration;
//            String parametro;
//            String strValoresURL = "";
//            enumeration = context.getRequestParameterNames();   // Leemos todos los parámetros recibidos por el URL
//            while (enumeration.hasMoreElements()) {
//                parametro = (String) enumeration.nextElement();
//                strValoresURL = strValoresURL + parametro + "=" + context.getParameter(parametro) + "<br>";
//            } // End while
//            context.putSessionObject("valoresURL",strValoresURL);
//
//            de.printStackTrace();
//        }
//        catch(Exception ioe) {
//            page="/vbtonline/pages/Error.jsp?pagina=reportePdf.java";
//            context.putSessionObject("excepcion",ioe);
//
//            Enumeration enumeration;
//            String parametro;
//            String strValoresURL = "";
//            enumeration = context.getRequestParameterNames();   // Leemos todos los parámetros recibidos por el URL
//            while (enumeration.hasMoreElements()) {
//                parametro = (String) enumeration.nextElement();
//                strValoresURL = strValoresURL + parametro + "=" + context.getParameter(parametro) + "<br>";
//            } // End while
//            context.putSessionObject("valoresURL",strValoresURL);
//
//            ioe.printStackTrace();
//        }
//        return baosPDF;
//    }
//
//    public String getEncodedURL(String url){
//        return null;
//    }
//    public ClientContext getClientContext(){
//        return context;
//    }
//    public void   setClientContext(ClientContext context){
//        this.context=context;
//    }
//    public String getNextPage(){
//        return context.getResponse().encodeURL(this.page);
//    }
//
//    protected void addDummyTable(Document doc) {
//        try {
//            com.lowagie.text.Table pdftable = new com.lowagie.text.Table(1);
//            pdftable.setPadding(0);
//            pdftable.setSpacing(0);
//            pdftable.setWidth(100f);
//            pdftable.setBorderWidth(0);
//            pdftable.setBorder(Rectangle.NO_BORDER);
//
//            Cell cell=new Cell(new Chunk(" ",FontFactory.getFont(FontFactory.TIMES,6, Font.BOLD)));
//            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell.setBorder(Rectangle.NO_BORDER);
//            pdftable.addCell(cell);
//
//            doc.add(pdftable);
//        } catch (Exception e) {
//            page="/vbtonline/pages/Error.jsp?pagina=reportePdf.java";
//            context.putSessionObject("excepcion",e);
//
//            Enumeration enumeration;
//            String parametro;
//            String strValoresURL = "";
//            enumeration = context.getRequestParameterNames();   // Leemos todos los parámetros recibidos por el URL
//            while (enumeration.hasMoreElements()) {
//                parametro = (String) enumeration.nextElement();
//                strValoresURL = strValoresURL + parametro + "=" + context.getParameter(parametro) + "<br>";
//            } // End while
//            context.putSessionObject("valoresURL",strValoresURL);
//
//            e.printStackTrace();
//        }
//    }
//
//    // Clase interna que atiende los eventos que se disparan mientras se construye el documento
//    class MyPageEvents extends PdfPageEventHelper {
//        // This is the contentbyte object of the writer
//        PdfContentByte cb;
//
//        // we will put the final number of pages in a template
//        PdfTemplate template;
//
//        // this is the BaseFont we are going to use for the header / footer
//        BaseFont bf = null;
//
//        // we override the onOpenDocument method
//        public void onOpenDocument(PdfWriter writer, Document document) {
//            try {
//                bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
//                cb = writer.getDirectContent();
//                template = cb.createTemplate(50, 50);
//            }
//            catch(DocumentException de) {
//            }
//            catch(IOException ioe) {
//            }
//        }
//
//        // we override the onEndPage method
//        public void onStartPage(PdfWriter writer, Document document) {
//            try {
//                String language    = (String)context.getSessionObject("language");
//                String locale      = (String)context.getSessionObject("locale");
//
//                String newlanguage = context.getParameter("nl");
//                String newlocale   = context.getParameter("nlo");
//
//                if( (!NullFormatter.isBlank(newlanguage)) && (!NullFormatter.isBlank(newlocale)) ) {
//                    language = newlanguage;
//                    locale   = newlocale;
//                    context.putSessionObject("language",language);
//                    context.putSessionObject("locale",locale);
//                } else if( (NullFormatter.isBlank(language)) && (NullFormatter.isBlank(locale)) ) {
//                    locale   = "VE";
//                    language = "es";
//                    context.putSessionObject("language",language);
//                    context.putSessionObject("locale",locale);
//                }
//
//                PageConfig pageConfig = new PageConfig("PDFCuentasEdoCuenta_"+locale+"_"+language,"vbtonline",locale,language);
//
//                String strNumeroCuenta = NullFormatter.formatBlank(context.getParameter("numeroCuenta"));
//                String strCmbMes       = NullFormatter.formatBlank(context.getParameter("cmbMes"));
//                String strTxtAño       = NullFormatter.formatBlank(context.getParameter("txtAño"));
//
//                /***********************************************************************************************
//                 *                               Encabezado del estado de cuenta                                *
//                 ***********************************************************************************************/
//                // Obtenemos los datos del encabezado del estado de cuenta.
//                String encabezadoSQL = "  SELECT CAB.NA_PRINCIPAL cliente_principal,"  // 0
//                        + "         CAB.CODMON moneda,"                   // 1
//                        + "         CAB.CODCAR identificador, "           // 2
//                        + "         '01/'||LPAD('" + strCmbMes + "', 2, '0')||'/" + strTxtAño + "' fecha_inicio,"  // 3
//                        + "         CAB.CODEJE ejecutivo, "                  // 4
//                        + "         CAB.NA_VARIOS clientes_secundarios, "    // 5
//                        + "         to_char(CAB.FECHA_FIN_MES, 'DD/MM/YYYY') fecha_fin, " // 6
//                        + "         CAB.GARANTIAB bloqueado,"  // 7
//                        + "         CAB.DIFERIDOB diferido,"   // 8
//                        + "         CAB.SALDISPONIBLE disponible,"  // 9
//                        + "         (CAB.GARANTIAB+CAB.DIFERIDOB+CAB.SALDISPONIBLE) saldo_actual," // 10
//                        + "         CAB.SALMESANTE saldo_anterior,"    // 11
//                        + "         CAB.CODCAR codigo_cartera,"    // 12
//                        + "         CAB.CODEJE codigo_ejecutivo,"    // 13
//                        + "         CAB.DIRENVIO direccion_envio"    // 14
//                        + "    FROM BANCO_VBT.EDO_CABECERA CAB"
//                        + "   WHERE CAB.CODEMP  = '0000009539'"
//                        + "     AND CAB.CODCOL  = '" + strNumeroCuenta + "'"
//                        + "     AND CAB.CODINST = 'CAH'"
//                        + "     AND to_char(CAB.FECHA_FIN_MES,'MMYYYY')   = LPAD('" + strCmbMes + strTxtAño + "', 6, '0')";
//
//                //System.out.println("PDFencabezadoSQL: " + encabezadoSQL);
//                com.venezolano.util.database.Row rowCliente = new com.venezolano.util.database.Row();
//                rowCliente.fillWithQuery(encabezadoSQL,"jdbc/sanluisDB");
//
//                strSaldoMesAnterior = "0";
//                strSaldoMesAnterior = rowCliente.getColumnAt(11);
//
//                com.lowagie.text.Table pdftable = new com.lowagie.text.Table(4);
//                pdftable.setPadding(2);
//                pdftable.setSpacing(0);
//                pdftable.setWidth(100f);
//                pdftable.setBorderWidth(0);
//
//                int headerwidths[] = {60,17,10,13};
//                pdftable.setWidths(headerwidths);
//
//                //Definos las letras
//                String strArchivoLetraArial = this.getClass().getResource("/vbtonline.properties").getPath();
//                strArchivoLetraArial = strArchivoLetraArial.substring(0,strArchivoLetraArial.length() - (new String("WEB-INF/classes/vbtonline.properties")).length());
//                strArchivoLetraArial += "fonts/arial.ttf";
//
//                BaseFont bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
//
//                //----------------
//                String archivo_temp = this.getClass().getResource("/vbtonline.properties").getPath();
//                archivo_temp = archivo_temp.substring(0,archivo_temp.length() - (new String("WEB-INF/classes/vbtonline.properties")).length());
//                archivo_temp += "images/comun/logoVenecredit.gif";
//
//                Image imgLogo = Image.getInstance(archivo_temp);
//                imgLogo.scalePercent(45);
//                Cell cell=new Cell(new Chunk(imgLogo, 0, -5));
//                cell.setBorder(Rectangle.NO_BORDER);
//                pdftable.addCell(cell);
//
//                cell=new Cell(new Chunk(pageConfig.getFieldLabel("titulo"),new Font(bfArial, sizeLetraTitulo, Font.BOLD)));
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                cell.setColspan(2);
//                cell.setBorder(Rectangle.NO_BORDER);
//                pdftable.addCell(cell);
//
//                cell=new Cell();
//                cell.setBorder(Rectangle.NO_BORDER);
//                pdftable.addCell(cell);
//                //----------------------------------
//                cell=new Cell();
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(10);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("subtitulo"),new Font(bfArial, sizeLetraSubtitulo, Font.BOLD)));
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//                cell.setColspan(2);
//                cell.setBorder(Rectangle.NO_BORDER);
//                pdftable.addCell(cell);
//
//                cell=new Cell();
//                cell.setBorder(Rectangle.NO_BORDER);
//                pdftable.addCell(cell);
//                //-----------------------------------------------------
//                cell=new Cell(new Chunk(pageConfig.getFieldLabel("primary"),new Font(bfArial, sizeLetraContenido, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("accountn"),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(strNumeroCuenta,new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell=new Cell();
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                //----------------------------------------
//                cell=new Cell(new Chunk(rowCliente.getColumnAt(0),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("identification"),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(NullFormatter.formatBlank(rowCliente.getColumnAt(12)),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell=new Cell();
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                //----------------------------------------
//                cell=new Cell();
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("forperiod"),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(NullFormatter.formatBlank(rowCliente.getColumnAt(3)),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell=new Cell();
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                //----------------------------------------
//                String clientesSecundarios = "";
//                String cliente = "";
//
//                clientesSecundarios = NullFormatter.formatBlank(rowCliente.getColumnAt(5));
//                clientesSecundarios = clientesSecundarios.trim();
//                if (!clientesSecundarios.equals("")) {
//                    if (clientesSecundarios.indexOf("-")!= -1) {
//                        cliente             = clientesSecundarios.substring(0,clientesSecundarios.indexOf("-"));
//                        clientesSecundarios = clientesSecundarios.substring((clientesSecundarios.indexOf("-")+1),clientesSecundarios.length());
//                    } else {
//                        cliente = clientesSecundarios;
//                        clientesSecundarios = "";
//                    }
//                }
//
//                cell=new Cell(new Chunk(cliente.trim(),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("through"),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(NullFormatter.formatBlank(rowCliente.getColumnAt(6)),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell=new Cell();
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                //----------------------------------------
//                cliente = "";
//
//                clientesSecundarios = clientesSecundarios.trim();
//                if (!clientesSecundarios.equals("")) {
//                    if (clientesSecundarios.indexOf("-")!= -1) {
//                        cliente             = clientesSecundarios.substring(0,clientesSecundarios.indexOf("-"));
//                        clientesSecundarios = clientesSecundarios.substring((clientesSecundarios.indexOf("-")+1),clientesSecundarios.length());
//                    } else {
//                        cliente = clientesSecundarios;
//                        clientesSecundarios = "";
//                    }
//                }
//
//                cell=new Cell(new Chunk(cliente.trim(),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("currency"),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(NullFormatter.formatBlank(rowCliente.getColumnAt(1)),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell=new Cell();
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                //----------------------------------------
//                cliente = "";
//
//                clientesSecundarios = clientesSecundarios.trim();
//                if (!clientesSecundarios.equals("")) {
//                    if (clientesSecundarios.indexOf("-")!= -1) {
//                        cliente             = clientesSecundarios.substring(0,clientesSecundarios.indexOf("-"));
//                        clientesSecundarios = clientesSecundarios.substring((clientesSecundarios.indexOf("-")+1),clientesSecundarios.length());
//                    } else {
//                        cliente = clientesSecundarios;
//                        clientesSecundarios = "";
//                    }
//                }
//
//                cell=new Cell(new Chunk(cliente.trim(),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("accountexecutive"),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(NullFormatter.formatBlank(rowCliente.getColumnAt(13)),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell=new Cell();
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                //----------------------------------------
//                cliente = "";
//
//                clientesSecundarios = clientesSecundarios.trim();
//                while(!clientesSecundarios.equals("")) {
//                    clientesSecundarios = clientesSecundarios.trim();
//                    if (clientesSecundarios.indexOf("-")!= -1) {
//                        cliente             = clientesSecundarios.substring(0,clientesSecundarios.indexOf("-"));
//                        clientesSecundarios = clientesSecundarios.substring((clientesSecundarios.indexOf("-")+1),clientesSecundarios.length());
//                    } else {
//                        cliente = clientesSecundarios;
//                        clientesSecundarios = "";
//                    }
//                    cell=new Cell(new Chunk(cliente.trim(),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                    cell.setBorder(Rectangle.NO_BORDER);
//                    cell.setLeading(5);
//                    pdftable.addCell(cell);
//
//                    cell = new Cell("");
//                    cell.setBorder(Rectangle.NO_BORDER);
//                    cell.setColspan(3);
//                    cell.setLeading(5);
//                    pdftable.addCell(cell);
//                }
//
//                //------------------------------------------
//                cell=new Cell(new Chunk(NullFormatter.formatBlank(rowCliente.getColumnAt(14)),new Font(bfArial, sizeLetraDireccion, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(10);
//                pdftable.addCell(cell);
//
//                cell = new Cell("");
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(10);
//                cell.setColspan(3);
//                pdftable.addCell(cell);
//                //----------------------------------------------
//                document.add(pdftable);
//                addDummyTable(document);
//                //----------------------------------------------
//                pdftable = new com.lowagie.text.Table(4);
//                pdftable.setPadding(2);
//                pdftable.setSpacing(0);
//                pdftable.setWidth(100f);
//                pdftable.setBorderWidth(0);
//                //pdftable.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
//
//                int headerwidths2[] = {25,25,25,25};
//                pdftable.setWidths(headerwidths2);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("deferred"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setBackgroundColor(backcolor);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setLeading(9);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("blocked"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setBackgroundColor(backcolor);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setLeading(9);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("available"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setBackgroundColor(backcolor);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setLeading(9);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("balance"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setBackgroundColor(backcolor);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setLeading(9);
//                pdftable.addCell(cell);
//                //-----------------------------------
//                cell = new Cell("");
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(2);
//                cell.setColspan(4);
//                pdftable.addCell(cell);
//                //-----------------------------------
//                cell = new Cell(new Chunk(NullFormatter.formatHtmlBlank(CurrencyFormatter.formatNumber(rowCliente.getColumnAt(8),2,",")),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(NullFormatter.formatHtmlBlank(CurrencyFormatter.formatNumber(rowCliente.getColumnAt(7),2,",")),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(NullFormatter.formatHtmlBlank(CurrencyFormatter.formatNumber(rowCliente.getColumnAt(9),2,",")),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(NullFormatter.formatHtmlBlank(CurrencyFormatter.formatNumber(rowCliente.getColumnAt(10),2,",")),new Font(bfArial, sizeLetraMensaje, Font.NORMAL)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setLeading(5);
//                pdftable.addCell(cell);
//                //-----------------------------------
//                cell = new Cell("");
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setLeading(2);
//                cell.setColspan(4);
//                pdftable.addCell(cell);
//                //--------------------------------------------------------------------
//                document.add(pdftable);
//                //-------------------------------------------------------------------
//                pdftable = new com.lowagie.text.Table(7);
//                pdftable.setPadding(2);
//                pdftable.setSpacing(0);
//                pdftable.setWidth(100f);
//                pdftable.setBorderWidth(0);
//                //pdftable.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
//
//                int headerwidths3[] = {12,12,25,12,12,12,15};
//                pdftable.setWidths(headerwidths3);
//
//                cell = new Cell("");
//                cell.setBorderWidth(2);
//                cell.setBorder(Rectangle.TOP);
//                cell.setLeading(5);
//                cell.setColspan(7);
//                pdftable.addCell(cell);
//
//                //------------------------------------------
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("tdate"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setLeading(10);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("vdate"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setLeading(10);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("description"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setLeading(10);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("reference"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setLeading(10);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("debit"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setLeading(10);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("credit"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setLeading(10);
//                pdftable.addCell(cell);
//
//                cell = new Cell(new Chunk(pageConfig.getFieldLabel("balance"),new Font(bfArial, sizeLetraMensaje, Font.BOLD)));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setLeading(10);
//                pdftable.addCell(cell);
//
//                //------------------------------------------
//                cell = new Cell("");
//                cell.setBorderWidth(1);
//                cell.setBorder(Rectangle.TOP);
//                cell.setLeading(5);
//                cell.setColspan(7);
//                pdftable.addCell(cell);
//
//                /******************************/
//                document.add(pdftable);
//
//            } catch(DocumentException de) {
//                page="/vbtonline/pages/Error.jsp?pagina=reportePdf.java";
//                context.putSessionObject("excepcion",de);
//
//                Enumeration enumeration;
//                String parametro;
//                String strValoresURL = "";
//                enumeration = context.getRequestParameterNames();   // Leemos todos los parámetros recibidos por el URL
//                while (enumeration.hasMoreElements()) {
//                    parametro = (String) enumeration.nextElement();
//                    strValoresURL = strValoresURL + parametro + "=" + context.getParameter(parametro) + "<br>";
//                } // End while
//                context.putSessionObject("valoresURL",strValoresURL);
//            }
//            catch(IOException ioe) {
//                page="/vbtonline/pages/Error.jsp?pagina=reportePdf.java";
//                context.putSessionObject("excepcion",ioe);
//
//                Enumeration enumeration;
//                String parametro;
//                String strValoresURL = "";
//                enumeration = context.getRequestParameterNames();   // Leemos todos los parámetros recibidos por el URL
//                while (enumeration.hasMoreElements()) {
//                    parametro = (String) enumeration.nextElement();
//                    strValoresURL = strValoresURL + parametro + "=" + context.getParameter(parametro) + "<br>";
//                } // End while
//                context.putSessionObject("valoresURL",strValoresURL);
//            }
//        }
//
//        // we override the onEndPage method
//        public void onEndPage(PdfWriter writer, Document document) {
//
//            String language    = (String)context.getSessionObject("language");
//            String locale      = (String)context.getSessionObject("locale");
//
//            String newlanguage = context.getParameter("nl");
//            String newlocale   = context.getParameter("nlo");
//
//            if( (!NullFormatter.isBlank(newlanguage)) && (!NullFormatter.isBlank(newlocale)) ) {
//                language = newlanguage;
//                locale   = newlocale;
//                context.putSessionObject("language",language);
//                context.putSessionObject("locale",locale);
//            } else if( (NullFormatter.isBlank(language)) && (NullFormatter.isBlank(locale)) ) {
//                locale   = "VE";
//                language = "es";
//                context.putSessionObject("language",language);
//                context.putSessionObject("locale",locale);
//            }
//
//            PageConfig pageConfig = new PageConfig("PDFCuentasEdoCuenta_"+locale+"_"+language,"vbtonline",locale,language);
//
//            BaseFont bfArial = bf;
//            //Definos las letras
//            try {
//                String strArchivoLetraArial = this.getClass().getResource("/vbtonline.properties").getPath();
//                strArchivoLetraArial = strArchivoLetraArial.substring(0,strArchivoLetraArial.length() - (new String("WEB-INF/classes/vbtonline.properties")).length());
//                strArchivoLetraArial += "fonts/arial.ttf";
//
//                bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
//            }
//            catch (Exception e) {
//                System.out.println("Error al cargar la letra en reportePdf.java - onEndPage()");
//                bfArial = bf;
//            }
//            /***********************************************************************
//             *                    Pie de Página                                     *
//             ***********************************************************************/
//            int pageN = writer.getPageNumber();
//            String text = " Pag. " + pageN + " / ";
//            float len = bf.getWidthPoint(text, 7);
//            cb.beginText();
//            cb.setFontAndSize(bfArial, 7);
//            cb.setTextMatrix(558, 60);
//            cb.showText(text);
//
//            cb.newlineText();
//            cb.setTextMatrix(20, 45);
//            cb.showText(pageConfig.getTagFieldValue("footerline1"));
//
//            cb.newlineText();
//            cb.setTextMatrix(20, 35);
//            cb.showText(pageConfig.getTagFieldValue("footerline2"));
//
//            cb.newlineText();
//            cb.setTextMatrix(20, 25);
//            cb.showText(pageConfig.getTagFieldValue("footerline3"));
//
//            cb.endText();
//            cb.addTemplate(template, 558 + len, 60);
//
//            // Grueso de la linea
//            cb.setLineWidth(2f);
//            // Coordenadas de la linea
//            cb.moveTo(20, 58);
//            cb.lineTo(594, 58);
//            // Pintar linea
//            cb.stroke();
//        }
//
//        // we override the onCloseDocument method
//        public void onCloseDocument(PdfWriter writer, Document document) {
//            BaseFont bfArial = bf;
//            //Definos las letras
//            try {
//                String strArchivoLetraArial = this.getClass().getResource("/vbtonline.properties").getPath();
//                strArchivoLetraArial = strArchivoLetraArial.substring(0,strArchivoLetraArial.length() - (new String("WEB-INF/classes/vbtonline.properties")).length());
//                strArchivoLetraArial += "fonts/arial.ttf";
//
//                bfArial = BaseFont.createFont(strArchivoLetraArial, BaseFont.WINANSI, BaseFont.EMBEDDED);
//            }
//            catch (Exception e) {
//                System.out.println("Error al cargar la letra en PDFCuentasEoCuenta.java - onCloseDocument()");
//                bfArial = bf;
//            }
//            template.beginText();
//            template.setFontAndSize(bfArial, 7);
//            template.showText(String.valueOf(writer.getPageNumber() - 1));
//            template.endText();
//        }
//    }
//}
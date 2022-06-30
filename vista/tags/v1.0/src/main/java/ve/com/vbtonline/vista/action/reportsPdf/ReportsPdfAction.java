package ve.com.vbtonline.vista.action.reportsPdf;


import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.opensymphony.xwork2.ActionSupport;
import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import sun.nio.cs.US_ASCII;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.reportsPdf.IReportsPdfServicio;
import ve.com.vbtonline.servicio.od.ContentSelectOd;
import ve.com.vbtonline.servicio.od.DataJson;
import ve.com.vbtonline.servicio.od.VBTUsersOd;
import ve.com.vbtonline.servicio.util.validacionUtil;

import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Jorge Maguina
 * Date: 28/11/2012
 * Time: 09:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReportsPdfAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(ReportsPdfAction.class);
    private FabricaTo fabrica;
    private String mensaje;
    private DataJson data=new DataJson();
    private Map session;
    private IReportsPdfServicio reportsPdfServicio;
    private InputStream inputStream;
    private String reporte;

    //Parameters pdf

    private String num_cta;
    private String mes;
    private String anio;
    private String cliente;
    private String monto;
    private String fechavencimiento;
    private String colocacion;
    private String moneda;
    private String referencia;
    private String fechaValor;
    private String tasadecambio;
    private String codproserv;
    private String banco;
    private String observacion;
    private String beneficiario;
    private String tasainteres;
    private String descripcion;
    private String fecha_operacion;
    private String tipMov;
    private String estatus;
    private String int_bon = "";
    private String tas_int = "";
    private String tasa_mora = "";
    private String periodo_fact = "";




    public ReportsPdfAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }

    public String execute() throws Exception {
        final String origen = "reportsPdfAction.execute";
        long time;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ReportsPdfAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+ReportsPdfAction.class+" | "+origen+" | "+time);



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
    public String reportEdoCuenta() throws Exception {
        reporte="EDC";
        final String origen = "reportPdfAction.reportEdoCuenta";
        long time;
        Color backcolor          = new Color(192,192,192);//new Color(245,245,245);
        int   sizeLetraTitulo    = 16;//19;
        int   sizeLetraSubtitulo = 12;//14;
        int   sizeLetraContenido = 9;//9;
        int   sizeLetraMensaje   = 8;//8;
        int   sizeLetraDireccion = 7;//7;
        validacionUtil validar = new validacionUtil();

        String msgPiedePagina1 = "In accordance with the applicable laws of the Cayman Islands and pursuant to a special resolution of the sole shareholder of Venecredit Bank & Trust, Ltd. (the \"Bank\") passed on ";
        String msgPiedePagina2 = "24 February 2012, the name of the Bank changed from \"Venecredit Bank & Trust, Ltd.\" to \"VBT Bank & Trust, Ltd.\"  All terms and conditions relating to the account agreements ";
        String msgPiedePagina3 = "between the Bank and its account holders remain unchanged.";
        try{

            num_cta=StringUtils.newStringUtf8(Base64.decodeBase64(num_cta.getBytes()));
            mes =  StringUtils.newStringUtf8(Base64.decodeBase64(mes.getBytes()));
            anio =  StringUtils.newStringUtf8(Base64.decodeBase64(anio.getBytes()));

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ReportsPdfAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            // Modo inicial el reporte
            ReportUtil reportUtil = new ReportUtil();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            Document document = reportUtil.createDocument("Report");
            PdfWriter writer = PdfWriter.getInstance(document, buffer);
            //instanciamos la clase HeaderFooter
            HeaderFooter event = new HeaderFooter();
            //Al haber un evento se reconocerá en la clase
            writer.setPageEvent(event);
            //Agregamos un titulo al pdf
            document.addTitle("Estado de Cuenta");
            //Agregamos el autor
            document.addAuthor("VBT");

            document.open();

            //////////////////////////////////////////////////////////////////////

            // Tabla superior Header
            float[] medidaCeldas = {6f,4f};
            PdfPTable TableCuentaCabecera = reportUtil.crearTabla(2,100,medidaCeldas);  // mueestra el Statement of Account y su respectiva imagen
            TableCuentaCabecera.setHorizontalAlignment(Element.ALIGN_LEFT);

            Paragraph tituloGrande = reportUtil.crearTituloGrande(14,"Savings Account\n\n\nStatement of Account");
            PdfPCell celltituloGrande = new PdfPCell(tituloGrande);
            celltituloGrande.setHorizontalAlignment(Element.ALIGN_LEFT);
            celltituloGrande.setBorder(Rectangle.NO_BORDER);

            //logo
            //se crea una imagen en una celda con los parametros ruta, tamano x, tamano y, porcentaje
            String archivo_temp = ServletActionContext.getServletContext().getRealPath("/");
            archivo_temp += "/resources/images/comun/logoVenecredit.gif";


            PdfPCell imagenCel = reportUtil.crearImagenCelda(archivo_temp,100,100,45);
            TableCuentaCabecera.addCell(imagenCel);
            TableCuentaCabecera.addCell(celltituloGrande);

            //crear tabla declaracion de cuenta
            medidaCeldas = new float[]{1f, 1f};
            PdfPTable TableDeclaracion = reportUtil.crearTabla(2,60,medidaCeldas);
            TableDeclaracion.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell cell;

            /////////// Info PL   encabecera esta la info
            List<String> cabecera = new ArrayList<String>();
            List<ContentSelectOd> cuentas = (List<ContentSelectOd> ) session.get("cuentasUsuario");
            if(validar.validarCuenta2(cuentas,num_cta.split(" ", 2)[0].trim()).equalsIgnoreCase("SI")){
                cabecera = reportsPdfServicio.consultarCabeceraEdoCuenta(num_cta.split(" ", 2)[0].trim(),mes,anio);


                ///
                cell= reportUtil.datosTabla("Account No:", Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);

                cell= reportUtil.datosTabla(num_cta.split(" ", 2)[0].trim(), Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);

                cell= reportUtil.datosTabla("Identification:", Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);

                cell= reportUtil.datosTabla(cabecera.get(2), Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);

                cell= reportUtil.datosTabla("For Period:", Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);

                cell= reportUtil.datosTabla(cabecera.get(3), Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);

                cell= reportUtil.datosTabla("Through:", Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);

                cell= reportUtil.datosTabla(cabecera.get(6), Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);

                cell= reportUtil.datosTabla("Currency:", Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);

                cell =reportUtil.datosTabla(cabecera.get(1), Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);

                cell =reportUtil.datosTabla("Account Executive:", Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);

                cell =reportUtil.datosTabla(cabecera.get(13), Element.ALIGN_LEFT);
                TableDeclaracion.addCell(cell);


                PdfPCell cellDeclaration = new PdfPCell(TableDeclaracion);
                cellDeclaration.setBorder(Rectangle.NO_BORDER);
                PdfPCell cellPrimary;
                cellPrimary = reportUtil.datosTabla(" Primary(ies): \n"+cabecera.get(0) +"\n\n HOLDMAIL", Element.ALIGN_LEFT);
                cellPrimary.setBorder(Rectangle.NO_BORDER);
                cellPrimary.setVerticalAlignment(Rectangle.ALIGN_TOP);

                TableCuentaCabecera.addCell(cellPrimary);
                TableCuentaCabecera.addCell(cellDeclaration);

                document.add(TableCuentaCabecera);

                //cear tabla separacion

                medidaCeldas = new float[]{1.5f};
                PdfPTable TableSeparacion= reportUtil.crearTabla(1,100,medidaCeldas);
                TableSeparacion.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell cellSeparador=new PdfPCell();

                TableSeparacion.addCell(cellSeparador);
                document.add(TableSeparacion);

                //se crea tabla balance
                medidaCeldas = new float[]{1f, 0.55f,0.55f,0.55f};
                PdfPTable TableBalance= reportUtil.crearTabla(4,100,medidaCeldas);

                //el encabezado de las tablas con formato

                cell= reportUtil.encabezadoTablaGris("Deferred (in transit)");
                cell.setBorder(Rectangle.NO_BORDER);

                TableBalance.addCell(cell);

                cell= reportUtil.encabezadoTablaGris("Blocked");
                cell.setBorder(Rectangle.NO_BORDER);
                TableBalance.addCell(cell);

                cell= reportUtil.encabezadoTablaGris("Available");
                cell.setBorder(Rectangle.NO_BORDER);
                TableBalance.addCell(cell);

                cell= reportUtil.encabezadoTablaGris("Balance carried forward");
                cell.setBorder(Rectangle.NO_BORDER);
                TableBalance.addCell(cell);

                //datos de la tabla de encabezado balance


                cell= reportUtil.datosTabla(CurrencyFormatter.formatNumber(cabecera.get(8), 2, ","),Element.ALIGN_CENTER);
                TableBalance.addCell(cell);

                cell= reportUtil.datosTabla(CurrencyFormatter.formatNumber(cabecera.get(7),2,","),Element.ALIGN_CENTER);
                TableBalance.addCell(cell);

                cell= reportUtil.datosTabla(CurrencyFormatter.formatNumber(cabecera.get(9), 2, ","),Element.ALIGN_CENTER);
                TableBalance.addCell(cell);

                cell= reportUtil.datosTabla(CurrencyFormatter.formatNumber(cabecera.get(10), 2, ","),Element.ALIGN_CENTER);
                TableBalance.addCell(cell);

                TableBalance.addCell(cell);


                document.add(TableBalance);


                Chunk chunkSeparador= reportUtil.separadorLigero();
                document.add(chunkSeparador);


                //tabla detalles movimientos
                medidaCeldas = new float[]{0.55f,0.55f , 1.0f, 0.55f, 0.55f, 0.55f, 0.55f};
                PdfPTable TableMovimiento= reportUtil.crearTabla(7,100,medidaCeldas);

                //////////Info PL////////////

                List<List<String>> tabla = new ArrayList<List<String>>();
                tabla = reportsPdfServicio.consultarTablaEdoCuenta(num_cta.split(" ", 2)[0].trim(),mes,anio);

                /////////////////////////////
                cell =reportUtil.datosTabla("Transaction Date",Element.ALIGN_CENTER, Font.BOLD);
                TableMovimiento.addCell(cell);

                cell =reportUtil.datosTabla("Value Date",Element.ALIGN_CENTER, Font.BOLD);
                TableMovimiento.addCell(cell);

                cell =reportUtil.datosTabla("Description",Element.ALIGN_LEFT, Font.BOLD);
                TableMovimiento.addCell(cell);

                cell =reportUtil.datosTabla("Reference",Element.ALIGN_CENTER, Font.BOLD);
                TableMovimiento.addCell(cell);

                cell =reportUtil.datosTabla("Debit", Element.ALIGN_RIGHT, Font.BOLD);
                TableMovimiento.addCell(cell);

                cell =reportUtil.datosTabla("Credit", Element.ALIGN_RIGHT, Font.BOLD);
                TableMovimiento.addCell(cell);

                cell =reportUtil.datosTabla("Balance carried forward", Element.ALIGN_RIGHT, Font.BOLD);
                TableMovimiento.addCell(cell);


                document.add(TableMovimiento);
                chunkSeparador= reportUtil.separadorLigero();
                document.add(chunkSeparador);
                TableMovimiento= reportUtil.crearTabla(7,100,medidaCeldas);

                medidaCeldas = new float[]{1.5f};
                PdfPTable TableSeparacion_space= reportUtil.crearTabla(1,100,medidaCeldas);
                TableSeparacion_space.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSeparador=new PdfPCell();

                cellSeparador= reportUtil.datosTabla("  ", Element.ALIGN_LEFT);
                TableSeparacion_space.addCell(cellSeparador);

                PdfPCell celldatos              =null;
                double saldo              = 0.0;
                double montoMovimiento    = 0.0;
                String primeraDescripcion = "";
                String primerSaldo        = "";
                int cantidad=24;
                int pag=1;
                int pos_tabla=0;
                //-------------------------- Movimientos del estado de cuenta ------------------------------------
                for (int i=0;i<tabla.size();i++,pos_tabla++) {
                    // Primera linea del detalle de los movimientos del estado de cuenta
                    if ( (i%cantidad) == 0 ) {   //  Se muestran 34 movimientos o transacciones por página
                        if ((i!=0) && ((i%cantidad) == 0) ) {
                            document.add(TableMovimiento);
                            TableMovimiento.deleteBodyRows();

                            document.newPage();

                            document.add(TableCuentaCabecera);
                            document.add(TableSeparacion);
                            document.add(TableBalance);

                        }
                        if(i == 0) {
//                        primeraDescripcion = pageConfig.getFieldLabel("balancebrought");
                            primeraDescripcion = "Balance Brought Forward";
                            primerSaldo        = CurrencyFormatter.formatNumber(cabecera.get(11),2,",");
                            saldo = Double.parseDouble(cabecera.get(11));

                        } else {
//                        primeraDescripcion = pageConfig.getFieldLabel("lastpagebalance");
                            primeraDescripcion = "Last Page Balance";
                            primerSaldo        = CurrencyFormatter.formatNumber(Double.toString(saldo),2,",");
                        }
                        //primera columna


                        cell =reportUtil.datosTabla("");
                        cell.setColspan(2);
                        TableMovimiento.addCell(cell);

                        cell =reportUtil.datosTabla(primeraDescripcion,Element.ALIGN_LEFT, Font.BOLD);
                        TableMovimiento.addCell(cell);

                        cell =reportUtil.datosTabla("");
                        cell.setColspan(3);
                        TableMovimiento.addCell(cell);

                        cell =reportUtil.datosTabla(primerSaldo, Element.ALIGN_RIGHT);
                        TableMovimiento.addCell(cell);
                        document.add(TableMovimiento);
                        TableMovimiento.deleteBodyRows();

                    }
                    //------------------- Resto de las lineas de los movimientos del estado de cuenta ---------------------------


                    cell =reportUtil.datosTabla(tabla.get(i).get(0), Element.ALIGN_CENTER);
                    TableMovimiento.addCell(cell);

                    cell =reportUtil.datosTabla(tabla.get(i).get(1), Element.ALIGN_CENTER);
                    TableMovimiento.addCell(cell);

                    cell =reportUtil.datosTabla(tabla.get(i).get(2), Element.ALIGN_LEFT);
                    TableMovimiento.addCell(cell);

                    if(tabla.get(i).get(3).equalsIgnoreCase("&nbsp;"))
                        cell =reportUtil.datosTabla("");
                    else
                        cell =reportUtil.datosTabla(tabla.get(i).get(3));
                    TableMovimiento.addCell(cell);


                    montoMovimiento = Double.parseDouble(NullFormatter.formatHtmlBlank(tabla.get(i).get(4)));
                    saldo = (saldo + montoMovimiento);

                    cell =reportUtil.datosTabla(montoMovimiento > 0 ? "":""+CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(montoMovimiento)),2,","), Element.ALIGN_RIGHT);
                    TableMovimiento.addCell(cell);


                    cell =reportUtil.datosTabla(montoMovimiento > 0 ? ""+ CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(montoMovimiento)), 2, ","):"", Element.ALIGN_RIGHT);
                    TableMovimiento.addCell(cell);

                    cell =reportUtil.datosTabla(CurrencyFormatter.formatNumber(Double.toString(saldo),2,","), Element.ALIGN_RIGHT);
                    TableMovimiento.addCell(cell);


                }
                if(tabla.size() == 0) {
                            primeraDescripcion = "Balance Brought Forward";
                            primerSaldo        = CurrencyFormatter.formatNumber(cabecera.get(11),2,",");
                            saldo = Double.parseDouble(cabecera.get(11));
                            cell =reportUtil.datosTabla("");
                            cell.setColspan(2);
                            TableMovimiento.addCell(cell);

                            cell =reportUtil.datosTabla(primeraDescripcion,Element.ALIGN_LEFT, Font.BOLD);
                            TableMovimiento.addCell(cell);

                            cell =reportUtil.datosTabla("");
                            cell.setColspan(3);
                            TableMovimiento.addCell(cell);

                            cell =reportUtil.datosTabla(primerSaldo, Element.ALIGN_RIGHT);
                            TableMovimiento.addCell(cell);
                            document.add(TableMovimiento);
                            TableMovimiento.deleteBodyRows();
                }
                document.add(TableMovimiento);
                document.close();

                VBTUsersOd usuario = (VBTUsersOd) session.get("user");
                this.getReportsPdfServicio().guardarLog(usuario.getLogin(),"13","2","6",num_cta.trim(),usuario.getIP(),"Edo. Cuenta Fecha:"+mes+"/"+anio+ " (N° Cartera:" + 	num_cta.split(" ")[2].trim() + ")");
                inputStream  =  new ByteArrayInputStream(buffer.toByteArray());
            }else{
                throw (new Exception ("No Existe",null));
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+ReportsPdfAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            inputStream=crearDocument_error();
            logger.error(e);
            setMensaje(e.getMessage());

        }
        return SUCCESS;
    }
    /**
     * Reporte de ColOperacion
     * @return  SUCCESS
     * @throws Exception
     */
    public String reportColOperation() throws Exception {
        reporte="COLOCACIONES";
        final String origen = "reportPdfAction.reportColOperation";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ReportsPdfAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            num_cta=StringUtils.newStringUtf8(Base64.decodeBase64(num_cta.getBytes()));
            fechavencimiento=StringUtils.newStringUtf8(Base64.decodeBase64(fechavencimiento.getBytes()));
            fecha_operacion=StringUtils.newStringUtf8(Base64.decodeBase64(fecha_operacion.getBytes()));
            fechaValor=StringUtils.newStringUtf8(Base64.decodeBase64(fechaValor.getBytes()));
            descripcion=StringUtils.newStringUtf8(Base64.decodeBase64(descripcion.getBytes()));
            referencia=StringUtils.newStringUtf8(Base64.decodeBase64(referencia.getBytes()));
            tasainteres=StringUtils.newStringUtf8(Base64.decodeBase64(tasainteres.getBytes()));
            monto=StringUtils.newStringUtf8(Base64.decodeBase64(monto.getBytes()));
            beneficiario=StringUtils.newStringUtf8(Base64.decodeBase64(beneficiario.getBytes()));
            banco=StringUtils.newStringUtf8(Base64.decodeBase64(banco.getBytes()));
            observacion=StringUtils.newStringUtf8(Base64.decodeBase64(observacion.getBytes()));
            moneda=StringUtils.newStringUtf8(Base64.decodeBase64(moneda.getBytes()));
            tasadecambio=StringUtils.newStringUtf8(Base64.decodeBase64(tasadecambio.getBytes()));
            colocacion=StringUtils.newStringUtf8(Base64.decodeBase64(colocacion.getBytes()));
            //Muestra Fecha actual
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateNow = formatter.format(currentDate.getTime());

            // Modo inicial el reporte
            ReportUtil reportUtil = new ReportUtil();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            Document document = reportUtil.createDocument("Report");
            PdfWriter writer = PdfWriter.getInstance(document, buffer);
            //instanciamos la clase HeaderFooter
            HeaderFooter event = new HeaderFooter();
            //Al haber un evento se reconocerá en la clase
            writer.setPageEvent(event);
            //Agregamos un titulo al pdf
            document.addTitle("Colocaciones");
            //Agregamos el autor
            document.addAuthor("VBT");

            document.open();

            // Tabla superior Header
            float[] medidaCeldas = {3.25f,1.60f};
            PdfPTable TableCabecera = reportUtil.crearTabla(2,100,medidaCeldas);  // muestra el Statement of Account y su respectiva imagen

            PdfPCell cellTime = reportUtil.encabezadoTablaAzul("TIME DEPOSIT PLACEMENT","LEFT");
            cellTime.setBorder(Rectangle.NO_BORDER);
            TableCabecera.addCell(cellTime);
            PdfPCell cellOperation = reportUtil.encabezadoTablaAzul("Operation Date: " +fecha_operacion,"RIGHT" );
            cellOperation.setBorder(Rectangle.NO_BORDER);
            TableCabecera.addCell(cellOperation);
            document.add(TableCabecera);

            //cear tabla de Cliente

            document.add(reportUtil.encabezadoIndice("CLIENT'S INFORMATION"));

            //cear tabla Cliente Detalle

            //////////Info PL////////////
            /////////// Info PL   encabecera esta la info
            List<String> cabecera = new ArrayList<String>();

            VBTUsersOd usuario = new VBTUsersOd();
            usuario = (VBTUsersOd) session.get("user");
            cliente = usuario.getCodpercli();

            cabecera = reportsPdfServicio.consultarDatosCliente(cliente);
            ///


            /////////////////////////////

            medidaCeldas = new float[]{3f,1.5f};
            PdfPTable TableClienteDetalle= reportUtil.crearTabla(2,90,medidaCeldas);

            PdfPCell cellClient = new PdfPCell();

            cellClient = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);

            cellClient = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);

            cellClient = reportUtil.datosTablaEncabezado("Client: "+cabecera.get(0),Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);

            cellClient = reportUtil.datosTablaEncabezado("C.I./R.I.F: "+cabecera.get(1),Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);

            cellClient = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);

            cellClient = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);

            cellClient = reportUtil.datosTablaEncabezado("Time Deposit N°: "+colocacion,Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);

            cellClient = reportUtil.datosTablaEncabezado("Maturity Date: "+fechavencimiento,Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);

            cellClient = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);

            cellClient = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);

            document.add(TableClienteDetalle);

            //tabla detalles movimientos
            medidaCeldas = new float[]{1f, 1f , 1f , 1f , 1f};
            PdfPTable TableMovimiento= reportUtil.crearTabla(5,90,medidaCeldas);
            PdfPCell cell;


            //////////Info PL////////////



            /////////////////////////////


            cell =reportUtil.datosTablaEncabezado("Amount", Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTablaEncabezado("Currency", Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTablaEncabezado("Value Date", Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTablaEncabezado("Reference", Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTablaEncabezado("Exchange rate", Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            TableMovimiento.addCell(cell);



            cell =reportUtil.datosTabla((monto.equalsIgnoreCase("_")) ?" ":monto);
            cell.setBorder(Rectangle.TOP);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTabla((moneda.equalsIgnoreCase("_")) ?" ":moneda);
            cell.setBorder(Rectangle.TOP);
            TableMovimiento.addCell(cell);

//        TableMovimiento.addCell(new Phrase("Balance Brought Forward",FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK)));

            cell =reportUtil.datosTabla((fechaValor.equalsIgnoreCase("_")) ?" ":fechaValor);
            cell.setBorder(Rectangle.TOP);
            TableMovimiento.addCell(cell);



            cell =reportUtil.datosTabla((referencia.equalsIgnoreCase("_")) ?" ":referencia);
            cell.setBorder(Rectangle.TOP);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTabla( (tasadecambio.equalsIgnoreCase("_")) ?" ":tasadecambio);
            cell.setBorder(Rectangle.TOP);
            TableMovimiento.addCell(cell);

            document.add(TableMovimiento);

            //Tabla de observaciones
            PdfPTable table_obse=reportUtil.encabezadoIndice("OBSERVATION");
            cell =reportUtil.datosTabla((observacion.equalsIgnoreCase("_")) ?" ":observacion);
            cell.setBorder(Rectangle.NO_BORDER);
            table_obse.addCell(cell);
            document.add(table_obse);


            document.close();


            inputStream  =  new ByteArrayInputStream(buffer.toByteArray());
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+ReportsPdfAction.class+" | "+origen+" | "+time);

            this.getReportsPdfServicio().guardarLog(usuario.getLogin(),"13","1","5",colocacion.trim(),usuario.getIP(),"PDF Detalle de Operación");

        }catch (Exception e){
            inputStream=crearDocument_error();
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;

    }
    /**
     * Reporte de cuenta - saldos y transacciones
     * @return  SUCCESS
     * @throws Exception
     */
    public String reportaccountBT()  {
        reporte="SALDOS_TRANSFERENCIA";
        final String origen = "reportPdfAction.reportaccountBT";
        long time;

        try{
            CurrencyFormatter formatoMonto = new CurrencyFormatter();
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ReportsPdfAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            //Muestra Fecha actual
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateNow = formatter.format(currentDate.getTime());

            // Modo inicial el reporte
            ReportUtil reportUtil = new ReportUtil();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            Document document = reportUtil.createDocument("Report");
            PdfWriter writer = PdfWriter.getInstance(document, buffer);
            //instanciamos la clase HeaderFooter
            HeaderFooter event = new HeaderFooter();
            //Al haber un evento se reconocerá en la clase
            writer.setPageEvent(event);
            //Agregamos un titulo al pdf
            document.addTitle("Saldos y Transferencias");
            //Agregamos el autor
            document.addAuthor("VBT");

            document.open();


            // Tabla superior Header
            float[] medidaCeldas = {3.25f,1.60f};
            PdfPTable TableCabecera = reportUtil.crearTabla(2,100,medidaCeldas);  // muestra el Statement of Account y su respectiva imagen

            num_cta=StringUtils.newStringUtf8(Base64.decodeBase64(num_cta.getBytes()));
            tasadecambio=StringUtils.newStringUtf8(Base64.decodeBase64(tasadecambio.getBytes()));
            descripcion=StringUtils.newStringUtf8(Base64.decodeBase64(descripcion.getBytes()));
            referencia=StringUtils.newStringUtf8(Base64.decodeBase64(referencia.getBytes()));
            fecha_operacion=StringUtils.newStringUtf8(Base64.decodeBase64(fecha_operacion.getBytes()));
            fechaValor=StringUtils.newStringUtf8(Base64.decodeBase64(fechaValor.getBytes()));
            monto=StringUtils.newStringUtf8(Base64.decodeBase64(monto.getBytes()));
            tipMov=StringUtils.newStringUtf8(Base64.decodeBase64(tipMov.getBytes()));
            mensaje=StringUtils.newStringUtf8(Base64.decodeBase64(mensaje.getBytes()));
            if(beneficiario!=null){
                beneficiario=StringUtils.newStringUtf8(Base64.decodeBase64(beneficiario.getBytes()));
            }
            if(observacion!=null){
                observacion=StringUtils.newStringUtf8(Base64.decodeBase64(observacion.getBytes()));
            }
            //cear tabla Cliente Detalle

            //////////Info PL////////////
            /////////// Info PL   encabecera esta la info
            List<String> cabecera = new ArrayList<String>();

            VBTUsersOd usuario = new VBTUsersOd();
            usuario = (VBTUsersOd) session.get("user");
            cliente = usuario.getCodpercli();
            logger.info("dato de consulta: "+cliente);
            cabecera = reportsPdfServicio.consultarDatosCliente(cliente);
            ///


            /////////////////////////////



            medidaCeldas = new float[]{3f,1.5f};
            PdfPTable TableClienteDetalle= reportUtil.crearTabla(2,90,medidaCeldas);

            PdfPCell cellClient = new PdfPCell();


            cellClient = reportUtil.datosTablaEncabezado("Client: "+cabecera.get(0),Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);

            cellClient = reportUtil.datosTablaEncabezado("C.I./R.I.F: "+cabecera.get(1),Element.ALIGN_LEFT);
            TableClienteDetalle.addCell(cellClient);


            cellClient = reportUtil.datosTablaEncabezado("Account N°: "+num_cta,Element.ALIGN_LEFT);
            cellClient.setColspan(2);
            TableClienteDetalle.addCell(cellClient);

            cellClient = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
            cellClient.setColspan(2);
            TableClienteDetalle.addCell(cellClient);




            /************************ DETALLES DE LA OPERACIÓN *********************************/
            medidaCeldas = new float[]{3f,1.5f};
            PdfPTable TableOperacionDetalle= reportUtil.crearTabla(2,90,medidaCeldas);
            PdfPCell cellDetalle = new PdfPCell();

            if ("CIC".equalsIgnoreCase(tipMov)) {
                PdfPCell cellTime = reportUtil.encabezadoTablaAzul(mensaje,"LEFT");
                cellTime.setBorder(Rectangle.NO_BORDER);
                TableCabecera.addCell(cellTime);
                PdfPCell cellOperation = reportUtil.encabezadoTablaAzul("Operation Date: " +fecha_operacion,"RIGHT" );
                cellOperation.setBorder(Rectangle.NO_BORDER);
                TableCabecera.addCell(cellOperation);

                cellDetalle=reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Client Id - Source: "+( ("_".equalsIgnoreCase(beneficiario)) ?" ":beneficiario),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Account N°- Source: "+( ("_".equalsIgnoreCase(descripcion)) ?" ":descripcion),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Originator: "+( ("_".equalsIgnoreCase(observacion)) ?" ":observacion),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);
            }else if ("CID".equalsIgnoreCase(tipMov)) {
                PdfPCell cellTime = reportUtil.encabezadoTablaAzul(mensaje,"LEFT");
                cellTime.setBorder(Rectangle.NO_BORDER);
                TableCabecera.addCell(cellTime);
                PdfPCell cellOperation = reportUtil.encabezadoTablaAzul("Operation Date: " +fecha_operacion,"RIGHT" );
                cellOperation.setBorder(Rectangle.NO_BORDER);
                TableCabecera.addCell(cellOperation);

                cellDetalle=reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Beneficiary: "+( ("_".equalsIgnoreCase(beneficiario)) ?" ":beneficiario),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Client Id - Destiny: "+( ("_".equalsIgnoreCase(descripcion)) ?" ":descripcion),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Account N° - Destiny: "+( ("_".equalsIgnoreCase(observacion)) ?" ":observacion),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);
            }else if ("TEI".equalsIgnoreCase(tipMov)) {
                PdfPCell cellTime = reportUtil.encabezadoTablaAzul(mensaje,"LEFT");
                cellTime.setBorder(Rectangle.NO_BORDER);
                TableCabecera.addCell(cellTime);
                PdfPCell cellOperation = reportUtil.encabezadoTablaAzul("Operation Date: " +fecha_operacion,"RIGHT" );
                cellOperation.setBorder(Rectangle.NO_BORDER);
                TableCabecera.addCell(cellOperation);

                cellDetalle=reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Originator: "+( ("_".equalsIgnoreCase(beneficiario)) ?" ":beneficiario),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Originators Bank: "+( ("_".equalsIgnoreCase(descripcion)) ?" ":descripcion),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Receiver Information: "+( ("_".equalsIgnoreCase(observacion)) ?" ":observacion),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);
            }else  if ("TEO".equalsIgnoreCase(tipMov)) {
                PdfPCell cellTime = reportUtil.encabezadoTablaAzul(mensaje,"LEFT");
                cellTime.setBorder(Rectangle.NO_BORDER);
                TableCabecera.addCell(cellTime);
                PdfPCell cellOperation = reportUtil.encabezadoTablaAzul("Operation Date: " +fecha_operacion,"RIGHT" );
                cellOperation.setBorder(Rectangle.NO_BORDER);
                TableCabecera.addCell(cellOperation);

                cellDetalle=reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Beneficiary: "+( ("_".equalsIgnoreCase(beneficiario)) ?" ":beneficiario),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Beneficiary Bank: "+( ("_".equalsIgnoreCase(descripcion)) ?" ":descripcion),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Beneficiary Information: "+( ("_".equalsIgnoreCase(observacion)) ?" ":observacion),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);
            }else{
                // tipo movimiento IDF  |
                PdfPCell cellTime = reportUtil.encabezadoTablaAzul(mensaje,"LEFT");
                cellTime.setBorder(Rectangle.NO_BORDER);
                TableCabecera.addCell(cellTime);
                PdfPCell cellOperation = reportUtil.encabezadoTablaAzul("Operation Date: " +fecha_operacion,"RIGHT" );
                cellOperation.setBorder(Rectangle.NO_BORDER);
                TableCabecera.addCell(cellOperation);

                cellDetalle=reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);

                cellDetalle=reportUtil.datosTablaEncabezado("Observation: "+( ("_".equalsIgnoreCase(observacion)) ?" ":observacion),Element.ALIGN_LEFT);
                cellDetalle.setColspan(2);
                TableOperacionDetalle.addCell(cellDetalle);
                monto=("_".equalsIgnoreCase(monto)) ?descripcion:monto;
            }

            cellDetalle=reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
            cellDetalle.setColspan(2);
            TableOperacionDetalle.addCell(cellDetalle);





            document.add(TableCabecera);
            document.add(reportUtil.encabezadoIndice("CLIENT'S INFORMATION"));
            document.add(TableClienteDetalle);
            document.add(reportUtil.encabezadoIndice("TRANSACTION'S INFORMATION "));
            document.add(TableOperacionDetalle);

            //tabla detalles movimientos
            medidaCeldas = new float[]{1f, 1f , 1f , 1f , 1f};
            PdfPTable TableMovimiento= reportUtil.crearTabla(5,90,medidaCeldas);
            PdfPCell cell;




            cell =reportUtil.datosTablaEncabezado("Amount", Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTablaEncabezado("Currency", Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTablaEncabezado("Value Date", Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTablaEncabezado("Reference", Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTablaEncabezado("Currency rate", Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            TableMovimiento.addCell(cell);



            cell =reportUtil.datosTabla(formatoMonto.formatNumber(monto,2,","));
            cell.setBorder(Rectangle.TOP);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTabla("USD$");
            cell.setBorder(Rectangle.TOP);
            TableMovimiento.addCell(cell);

//        TableMovimiento.addCell(new Phrase("Balance Brought Forward",FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK)));

            cell =reportUtil.datosTabla(("_".equalsIgnoreCase(fechaValor)) ?" ":fechaValor);
            cell.setBorder(Rectangle.TOP);
            TableMovimiento.addCell(cell);



            cell =reportUtil.datosTabla(("_".equalsIgnoreCase(referencia)) ?" ":referencia);
            cell.setBorder(Rectangle.TOP);
            TableMovimiento.addCell(cell);

            cell =reportUtil.datosTabla( (tasadecambio.equalsIgnoreCase("_")) ?" ":tasadecambio);
            cell.setBorder(Rectangle.TOP);
            TableMovimiento.addCell(cell);

            document.add(TableMovimiento);


            document.close();
            this.getReportsPdfServicio().guardarLog(usuario.getLogin(),"13","1","4",num_cta.trim(),usuario.getIP(),"PDF Detalle de transacción");

            inputStream  =  new ByteArrayInputStream(buffer.toByteArray());
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+ReportsPdfAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            inputStream=crearDocument_error();
            logger.error(e);
            setMensaje(e.getMessage());




        }
        return SUCCESS;

    }
    /**
     * Reporte de ColOperacion
     * @return  SUCCESS
     * @throws Exception
     */
    public String reportCertificados() throws Exception {
        final String origen = "reportPdfAction.reportCertificados";
        long time;
        reporte="CERTIFICADOS";
        validacionUtil validar = new validacionUtil();
        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ReportsPdfAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            num_cta=StringUtils.newStringUtf8(Base64.decodeBase64(num_cta.getBytes()));
            colocacion=StringUtils.newStringUtf8(Base64.decodeBase64(colocacion.getBytes()));
            fechaValor=StringUtils.newStringUtf8(Base64.decodeBase64(fechaValor.getBytes()));
            fechavencimiento=StringUtils.newStringUtf8(Base64.decodeBase64(fechavencimiento.getBytes()));
            moneda=StringUtils.newStringUtf8(Base64.decodeBase64(moneda.getBytes()));
            descripcion=StringUtils.newStringUtf8(Base64.decodeBase64(descripcion.getBytes()));
            tasainteres=StringUtils.newStringUtf8(Base64.decodeBase64(tasainteres.getBytes()));
            monto=StringUtils.newStringUtf8(Base64.decodeBase64(monto.getBytes()));
            estatus=StringUtils.newStringUtf8(Base64.decodeBase64(estatus.getBytes()));

            // Modo inicial el reporte
            ReportUtil reportUtil = new ReportUtil();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            Document document = reportUtil.createDocument("Report");
            PdfWriter writer = PdfWriter.getInstance(document, buffer);
            //instanciamos la clase HeaderFooter
            HeaderFooter event = new HeaderFooter();
            //Al haber un evento se reconocerá en la clase
            writer.setPageEvent(event);
            //Agregamos un titulo al pdf
            document.addTitle("Certificados");
            //Agregamos el autor
            document.addAuthor("VBT");


            //*Llamado al primer PL*//
            List<String> cabecera = new ArrayList<String>();
            List<ContentSelectOd> colocaciones = (List<ContentSelectOd> ) session.get("colocaciones");

            if(validar.validarColocacion(colocaciones,colocacion.trim()).equalsIgnoreCase("SI") ){


                cabecera = reportsPdfServicio.consultarCeritificadoApertura(colocacion.trim());


                document.open();
                //////////////////////////////////////////////////////////////////////

                // Tabla superior Header
                float[] medidaCeldas = {8f,2f};
                PdfPTable TableCuentaCabecera = reportUtil.crearTabla(2,80,medidaCeldas);  // mueestra el Statement of Account y su respectiva imagen


                TableCuentaCabecera.setHorizontalAlignment(Element.ALIGN_CENTER);

                String archivo_temp = ServletActionContext.getServletContext().getRealPath("/");
                archivo_temp +="/resources/images/comun/logoVenecredit.gif";
                PdfPCell imagenCel_logo = reportUtil.crearImagenCelda(archivo_temp,150,150,60);
                imagenCel_logo.setVerticalAlignment(Rectangle.ALIGN_TOP);
                imagenCel_logo.setColspan(2);
                TableCuentaCabecera.addCell(imagenCel_logo);

                PdfPCell letras= reportUtil.datosTabla(cabecera.get(11).replace(",","").substring(0,cabecera.get(11).lastIndexOf(" ")).concat(",").concat(cabecera.get(11).substring(cabecera.get(11).lastIndexOf(" "))),Element.ALIGN_RIGHT);
                letras.setColspan(2);
                TableCuentaCabecera.addCell(letras);

                letras = reportUtil.datosTabla("\n\n\nTo:   "+cabecera.get(0)+"\n        "+cabecera.get(13)+"\n",Element.ALIGN_LEFT);
                letras.setColspan(2);
                TableCuentaCabecera.addCell(letras);

                letras = reportUtil.datosTabla(cabecera.get(1)+" - "+num_cta,Element.ALIGN_LEFT);
                TableCuentaCabecera.addCell(letras);
                letras = reportUtil.datosTabla("No. "+colocacion,Element.ALIGN_RIGHT);
                TableCuentaCabecera.addCell(letras);

                for(int i=0;i<6;i++){
                    letras= reportUtil.datosTabla("  ", Element.ALIGN_LEFT);
                    letras.setColspan(2);
                    TableCuentaCabecera.addCell(letras);
                }

                letras = reportUtil.datosTabla("We confirm your deposit as follows:",Element.ALIGN_LEFT);
                letras.setColspan(2);
                TableCuentaCabecera.addCell(letras);

                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(2);
                TableCuentaCabecera.addCell(letras);

                document.add(TableCuentaCabecera);

                float[] medidaCeldas_c = {6f,2f,2f};
                PdfPTable TableCertificados = reportUtil.crearTabla(3,80,medidaCeldas_c);  // mueestra el Statement of Account y su respectiva imagen
                TableCertificados.setHorizontalAlignment(Element.ALIGN_CENTER);


                letras = reportUtil.datosTabla("    Principal ...........................................................................",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(cabecera.get(3),Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(descripcion,Element.ALIGN_RIGHT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);

                letras = reportUtil.datosTabla("    Interest Rate .....................................................................",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(" ",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(tasainteres,Element.ALIGN_RIGHT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);

                letras = reportUtil.datosTabla("    Value Date .........................................................................",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(" ",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(fechaValor,Element.ALIGN_RIGHT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);

                letras = reportUtil.datosTabla("    Maturity Date ....................................................................",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(fechavencimiento,Element.ALIGN_RIGHT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);

                letras = reportUtil.datosTabla("    Interest ..............................................................................",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(cabecera.get(3),Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(cabecera.get(8),Element.ALIGN_RIGHT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);

                letras = reportUtil.datosTabla("    Total Due at Maturity.............................................................",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(cabecera.get(3),Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(monto,Element.ALIGN_RIGHT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n\n\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);

                letras = reportUtil.datosTabla("Special Conditions",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("Should you require any information regarding your account(s)please feel free to contact our Customer Service Desk in the Cayman Islands at 345-040-6917. fax 345-040-8017 from 8:00 a.m. to 4:30 p.m. (EST) from monday to friday or by e-mail address:VIB@candw.ky",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("Origin of funds:",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras)  ;
                document.add(TableCertificados);


                //////////Info PL////////////

                List<List<String>> tabla = new ArrayList<List<String>>();
                tabla = reportsPdfServicio.consultarmovimientosCertificado(colocacion.trim(),estatus);

                /////////////////////////////



                float[] medidaCeldas_c2 = {4f,3f,1f,2f};
                PdfPTable TableCertificados2 = reportUtil.crearTabla(4,80,medidaCeldas_c2);  // mueestra el Statement of Account y su respectiva imagen
                TableCertificados2.setHorizontalAlignment(Element.ALIGN_CENTER);

                for(int f=0;f<tabla.size();f++){

                    letras = reportUtil.datosTabla(tabla.get(f).get(0),Element.ALIGN_LEFT);
                    TableCertificados2.addCell(letras);
                    letras = reportUtil.datosTabla(tabla.get(f).get(5).equalsIgnoreCase("&nbsp;")?" ":tabla.get(f).get(5),Element.ALIGN_LEFT);
                    TableCertificados2.addCell(letras);
                    letras = reportUtil.datosTabla(tabla.get(f).get(4),Element.ALIGN_RIGHT);
                    TableCertificados2.addCell(letras);
                    letras = reportUtil.datosTabla(tabla.get(f).get(1),Element.ALIGN_RIGHT);
                    TableCertificados2.addCell(letras);
                }


                document.add(TableCertificados2);

                document.close();
                VBTUsersOd   usuario = (VBTUsersOd) session.get("user");
                this.getReportsPdfServicio().guardarLog(usuario.getLogin(),"13","1","5",colocacion.trim(),usuario.getIP(),"Certificado de Apertura");
                inputStream  =  new ByteArrayInputStream(buffer.toByteArray());
            }else if(estatus.equalsIgnoreCase("vencida")){
                cabecera = reportsPdfServicio.consultarCeritificadoApertura(colocacion.trim());

                document.open();
                //////////////////////////////////////////////////////////////////////

                // Tabla superior Header
                float[] medidaCeldas = {8f,2f};
                PdfPTable TableCuentaCabecera = reportUtil.crearTabla(2,80,medidaCeldas);  // mueestra el Statement of Account y su respectiva imagen


                TableCuentaCabecera.setHorizontalAlignment(Element.ALIGN_CENTER);

                String archivo_temp = ServletActionContext.getServletContext().getRealPath("/");
                archivo_temp +="/resources/images/comun/logoVenecredit.gif";
                PdfPCell imagenCel_logo = reportUtil.crearImagenCelda(archivo_temp,150,150,60);
                imagenCel_logo.setVerticalAlignment(Rectangle.ALIGN_TOP);
                imagenCel_logo.setColspan(2);
                TableCuentaCabecera.addCell(imagenCel_logo);

                PdfPCell letras= reportUtil.datosTabla(cabecera.get(11).replace(",","").substring(0,cabecera.get(11).lastIndexOf(" ")).concat(",").concat(cabecera.get(11).substring(cabecera.get(11).lastIndexOf(" "))),Element.ALIGN_RIGHT);
                letras.setColspan(2);
                TableCuentaCabecera.addCell(letras);

                letras = reportUtil.datosTabla("\n\n\nTo:   "+cabecera.get(0)+"\n        "+cabecera.get(13)+"\n",Element.ALIGN_LEFT);
                letras.setColspan(2);
                TableCuentaCabecera.addCell(letras);

                letras = reportUtil.datosTabla(cabecera.get(1)+" - "+num_cta,Element.ALIGN_LEFT);
                letras.setColspan(2);
                TableCuentaCabecera.addCell(letras);

                for(int i=0;i<6;i++){
                    letras= reportUtil.datosTabla("  ", Element.ALIGN_LEFT);
                    letras.setColspan(2);
                    TableCuentaCabecera.addCell(letras);
                }

                letras = reportUtil.datosTabla("Your deposit No.  "+colocacion,Element.ALIGN_LEFT);
                letras.setColspan(2);
                TableCuentaCabecera.addCell(letras);
                letras = reportUtil.datosTabla("is being repaid as follows:",Element.ALIGN_LEFT);
                letras.setColspan(2);
                TableCuentaCabecera.addCell(letras);

                for(int i=0;i<5;i++){
                    letras= reportUtil.datosTabla("  ", Element.ALIGN_LEFT);
                    letras.setColspan(2);
                    TableCuentaCabecera.addCell(letras);
                }

                document.add(TableCuentaCabecera);

                float[] medidaCeldas_c = {7f,1f,2f};
                PdfPTable TableCertificados = reportUtil.crearTabla(3,80,medidaCeldas_c);  // mueestra el Statement of Account y su respectiva imagen
                TableCertificados.setHorizontalAlignment(Element.ALIGN_CENTER);


                letras = reportUtil.datosTabla("Principal ........................................................................",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(cabecera.get(3),Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(descripcion,Element.ALIGN_RIGHT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);

                letras = reportUtil.datosTabla("Interest .........................................................................",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(cabecera.get(3),Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(cabecera.get(8),Element.ALIGN_RIGHT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);

                letras = reportUtil.datosTabla("Total Due at Maturity.............................................................",Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(cabecera.get(3),Element.ALIGN_LEFT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla(monto,Element.ALIGN_RIGHT);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);

                for(int i=0;i<4;i++){
                    letras= reportUtil.datosTabla("  ", Element.ALIGN_LEFT);
                    letras.setColspan(3);
                    TableCertificados.addCell(letras);
                }

                letras = reportUtil.datosTabla("We credit: "+cabecera.get(12),Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);
                letras = reportUtil.datosTabla("\n\n",Element.ALIGN_LEFT);
                letras.setColspan(3);
                TableCertificados.addCell(letras);
                document.add(TableCertificados);


                //////////Info PL////////////

                List<List<String>> tabla = new ArrayList<List<String>>();
                tabla = reportsPdfServicio.consultarmovimientosCertificado(colocacion.trim(),estatus);

                /////////////////////////////



                float[] medidaCeldas_c2 = {4f,3f,1f,2f};
                PdfPTable TableCertificados2 = reportUtil.crearTabla(4,80,medidaCeldas_c2);  // mueestra el Statement of Account y su respectiva imagen
                TableCertificados2.setHorizontalAlignment(Element.ALIGN_CENTER);

                for(int f=0;f<tabla.size();f++){
                    if(!tabla.get(f).get(2).equals("IN")){
                        letras = reportUtil.datosTabla(tabla.get(f).get(0),Element.ALIGN_LEFT);
                        TableCertificados2.addCell(letras);
                        letras = reportUtil.datosTabla(tabla.get(f).get(5).equalsIgnoreCase("&nbsp;")?" ":tabla.get(f).get(5),Element.ALIGN_LEFT);
                        TableCertificados2.addCell(letras);
                        letras = reportUtil.datosTabla(tabla.get(f).get(4),Element.ALIGN_RIGHT);
                        TableCertificados2.addCell(letras);
                        letras = reportUtil.datosTabla(tabla.get(f).get(1),Element.ALIGN_RIGHT);
                        TableCertificados2.addCell(letras);
                    }
                }



                document.add(TableCertificados2);

                document.close();

                VBTUsersOd   usuario = (VBTUsersOd) session.get("user");
                this.getReportsPdfServicio().guardarLog(usuario.getLogin(),"13","1","5",colocacion.trim(),usuario.getIP(),"Certificado de Vencimiento");
                inputStream  =  new ByteArrayInputStream(buffer.toByteArray());
            }else{
                throw (new Exception ("No existe",null));
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+ReportsPdfAction.class+" | "+origen+" | "+time);
        }catch (Exception e){
            inputStream=crearDocument_error();
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }
    /**
     *  Reportes de estado de cuenta de TDC
     * @return
     * @throws Exception
     */
    public String reportEdoCuentaTDC() throws Exception {
        reporte="TDC";
        final String origen = "reportPdfAction.reportEdoCuentaTDC";
        long time;

        String num_cta_plast_ppal = "";
        String nombre_cliente = "";
        String direccion_uno = "";
        String direccion_dos = "";
        String direccion_tres = "";
        String zona_postal = "";
        String lim_credito = "";
        String credito_disp = "";
        String fecha_factura = "";
        String pag_total = "";
        String pag_min_mes = "";
        String fec_pag_antes = "";
        String sal_anterior = "";
        String cargos = "";
        String abonos = "";
        String saldo_actual = "";
        String cuo_ven = "";
        String imp_ven = "";
        String cuo_mes = "";

        String tpo_tdc = "";
        String observaciones = "";
        String strObservacionLog="";
        validacionUtil validar = new validacionUtil();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ReportsPdfAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            num_cta=StringUtils.newStringUtf8(Base64.decodeBase64(num_cta.getBytes()));
            mes=StringUtils.newStringUtf8(Base64.decodeBase64(mes.getBytes()));
            //////////Info PL////////////

            List<String> cabecera = new ArrayList<String>();
            codproserv="002";
            List<ContentSelectOd> tarjetas = (List<ContentSelectOd> ) session.get("tarjetas");
            if(validar.validarTDC(tarjetas,num_cta.trim()).equalsIgnoreCase("SI")){
                cabecera = reportsPdfServicio.consultarCabeceraEdoCuentaTDC(num_cta.trim(),codproserv,mes);
                CurrencyFormatter formatoMonto = new CurrencyFormatter();

                if(!cabecera.isEmpty()){
                    num_cta_plast_ppal = NullFormatter.formatBlank(cabecera.get(0));
                    nombre_cliente = NullFormatter.formatBlank(cabecera.get(2));
                    direccion_uno = NullFormatter.formatBlank(cabecera.get(3));
                    direccion_dos = NullFormatter.formatBlank(cabecera.get(4));
                    direccion_tres = NullFormatter.formatBlank(cabecera.get(5));
                    zona_postal = NullFormatter.formatBlank(cabecera.get(6));
                    lim_credito = (new Double(cabecera.get(7))!=null) ? formatoMonto.formatNumber(cabecera.get(7),2,","):"&nbsp;";
                    //credito_disp = (new Double(rsEncab.getDouble("credito_disp"))!=null)?formatNum.formatoBigDoble(rsEncab.getDouble("credito_disp"),".",2):"&nbsp;";
                    credito_disp = (new Double(cabecera.get(8))!=null) ? formatoMonto.formatNumber(cabecera.get(8),2,","):"&nbsp;";
                    fecha_factura = cabecera.get(9);
                    pag_total = (new Double(cabecera.get(10))!=null) ? formatoMonto.formatNumber(cabecera.get(10),2,","):"&nbsp;";
                    pag_min_mes = (new Double(cabecera.get(11))!=null) ? formatoMonto.formatNumber(cabecera.get(11),2,","):"&nbsp;";
                    fec_pag_antes = cabecera.get(12);
                    sal_anterior = (new Double(cabecera.get(21))!=null) ? formatoMonto.formatNumber(cabecera.get(21),2,","):"&nbsp;";
                    cargos = (new Double(cabecera.get(22))!=null) ? formatoMonto.formatNumber(cabecera.get(22),2,","):"&nbsp;";
                    abonos = (new Double(cabecera.get(23))!=null) ? formatoMonto.formatNumber(cabecera.get(23),2,","):"&nbsp;";
                    saldo_actual = (new Double(cabecera.get(13))!=null) ? formatoMonto.formatNumber(cabecera.get(13),2,","):"&nbsp;";
                    cuo_ven = NullFormatter.formatBlank(cabecera.get(14));
                    imp_ven = (new Double(cabecera.get(15))!=null) ? formatoMonto.formatNumber(cabecera.get(15),2,","):"&nbsp;";
                    cuo_mes = (new Double(cabecera.get(16))!=null) ? formatoMonto.formatNumber(cabecera.get(16),2,","):"&nbsp;";
                    int_bon = (new Double(cabecera.get(17))!=null) ? formatoMonto.formatNumber(cabecera.get(17),4,","):"&nbsp;";
                    tas_int = (new Double(cabecera.get(18))!=null) ? formatoMonto.formatNumber(cabecera.get(18),4,","):"&nbsp;";
                    tasa_mora = (new Double(cabecera.get(19))!=null)?formatoMonto.formatNumber(cabecera.get(19),2,","):"&nbsp;";
                    periodo_fact = NullFormatter.formatBlank(cabecera.get(20));
                    tpo_tdc = NullFormatter.formatBlank(cabecera.get(24));
                    observaciones = NullFormatter.formatBlank(cabecera.get(25));
                }

                /////////////////////////////

                // Modo inicial el reporte
                ReportUtil reportUtil = new ReportUtil();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                Document document = reportUtil.createDocument("Report");
                PdfWriter writer = PdfWriter.getInstance(document, buffer);
                //instanciamos la clase HeaderFooter
                HeaderFooter event = new HeaderFooter();
                //Al haber un evento se reconocerá en la clase
                writer.setPageEvent(event);
                //Agregamos un titulo al pdf
                document.addTitle("Estado de Cuenta");
                //Agregamos el autor
                document.addAuthor("VBT");

                document.open();


                //////////////////////////////////////////////////////////////////////

                // Tabla superior Header
                float[] medidaCeldas = {5f,5f};
                PdfPTable TableCuentaCabecera = reportUtil.crearTabla(2,100,medidaCeldas);  // mueestra el Statement of Account y su respectiva imagen
                TableCuentaCabecera.setHorizontalAlignment(Element.ALIGN_LEFT);

                PdfPTable TableStatement_visa = reportUtil.crearTabla(2,100,medidaCeldas);  // mueestra el Statement of Account y su respectiva imagen
                TableCuentaCabecera.setHorizontalAlignment(Element.ALIGN_LEFT);

                Paragraph tituloGrande = reportUtil.crearTituloGrande(14,"Account Statement ");
                PdfPCell celltituloGrande = new PdfPCell(tituloGrande);
                celltituloGrande.setHorizontalAlignment(Element.ALIGN_LEFT);
                celltituloGrande.setBorder(Rectangle.NO_BORDER);
                TableStatement_visa.addCell(celltituloGrande);
                String archivo_temp = ServletActionContext.getServletContext().getRealPath("/");
                if("V".equalsIgnoreCase(tpo_tdc)){
                    archivo_temp += "/resources/images/comun/logo_visa.jpg";
                }else{
                    archivo_temp += "/resources/images/comun/logo_master.gif";
                }
                PdfPCell imagenCel_tarjeta = reportUtil.crearImagenCelda(archivo_temp,50,50,45);
                imagenCel_tarjeta.setHorizontalAlignment(Element.ALIGN_LEFT);
                imagenCel_tarjeta.setVerticalAlignment(Element.ALIGN_TOP);
                imagenCel_tarjeta.setBorder(Rectangle.NO_BORDER);
                TableStatement_visa.addCell(imagenCel_tarjeta);




                //logo
                //se crea una imagen en una celda con los parametros ruta, tamano x, tamano y, porcentaje
                archivo_temp = ServletActionContext.getServletContext().getRealPath("/");
                archivo_temp += "/resources/images/comun/logoVenecredit.gif";


                PdfPCell imagenCel_logo = reportUtil.crearImagenCelda(archivo_temp,100,100,45);
                imagenCel_logo.setVerticalAlignment(Rectangle.ALIGN_TOP);
                TableCuentaCabecera.addCell(imagenCel_logo);
                PdfPCell cellStatement_visa= new PdfPCell(TableStatement_visa);
                cellStatement_visa.setBorder(Rectangle.NO_BORDER);
                TableCuentaCabecera.addCell(cellStatement_visa);






                // CREANDO TABLA PARA DATOS DE PERSONA
                PdfPTable TableDatosPersona = new PdfPTable(2);
                TableDatosPersona.setWidthPercentage(100);
                TableCuentaCabecera.setHorizontalAlignment(Element.ALIGN_LEFT);

                Paragraph nombrePersona = reportUtil.crearTituloGrande(8,nombre_cliente);
                PdfPCell cellnombrePersona = new PdfPCell(nombrePersona);
                cellnombrePersona.setColspan(2);
                cellnombrePersona.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellnombrePersona.setBorder(Rectangle.NO_BORDER);

                TableDatosPersona.addCell(cellnombrePersona);

                PdfPCell  celldatos = new PdfPCell(reportUtil.formatoValor(8,direccion_uno));
                celldatos.setColspan(2);
                celldatos.setBorder(Rectangle.NO_BORDER);
                TableDatosPersona.addCell(celldatos);

                celldatos = new PdfPCell(reportUtil.formatoValor(8,direccion_dos));
                celldatos.setColspan(2);
                celldatos.setBorder(Rectangle.NO_BORDER);
                TableDatosPersona.addCell(celldatos);

                celldatos = new PdfPCell(reportUtil.formatoValor(8,direccion_tres));
                celldatos.setColspan(2);
                celldatos.setBorder(Rectangle.NO_BORDER);
                TableDatosPersona.addCell(celldatos);

                // AGREGANDO DATOS DE LA PERSONA
                celldatos = new PdfPCell(TableDatosPersona);
                celldatos.setBorder(Rectangle.NO_BORDER);
                TableCuentaCabecera.addCell(celldatos);

                // CREANDO TABLA PARA DATOS DE LA TARJETA DE CREDITO
                PdfPTable TableDatosTarjetaCredito = new PdfPTable(3);
                TableDatosTarjetaCredito.setWidthPercentage(100);
                TableDatosTarjetaCredito.setHorizontalAlignment(Element.ALIGN_CENTER);
                //el encabezado de las tablas con formato
                celldatos= reportUtil.encabezadoTablaGris("Account Number");
                celldatos.setColspan(2);
                celldatos.setHorizontalAlignment(Element.ALIGN_LEFT);
                celldatos.setBorder(Rectangle.TOP);
                celldatos.setBorderColorTop(new BaseColor(0,0,0));
                TableDatosTarjetaCredito.addCell(celldatos);

                celldatos= reportUtil.encabezadoTablaGris("Page");
                celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                celldatos.setBorder(Rectangle.TOP);
                celldatos.setBorderColorTop(new BaseColor(0,0,0));
                TableDatosTarjetaCredito.addCell(celldatos);

                celldatos = new PdfPCell(reportUtil.formatoValor(8,num_cta_plast_ppal));
                celldatos.setColspan(2);
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                TableDatosTarjetaCredito.addCell(celldatos);

                celldatos = new PdfPCell(reportUtil.formatoValor(8,String.valueOf(document.getPageNumber()+1)));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                TableDatosTarjetaCredito.addCell(celldatos);


                celldatos= reportUtil.encabezadoTablaGris("Credit Limit");
                celldatos.setBorder(Rectangle.TOP);
                celldatos.setBorderColorTop(new BaseColor(0,0,0));
                TableDatosTarjetaCredito.addCell(celldatos);

                celldatos= reportUtil.encabezadoTablaGris("Available Credit Limit");
                celldatos.setBorder(Rectangle.TOP);
                celldatos.setBorderColorTop(new BaseColor(0,0,0));
                TableDatosTarjetaCredito.addCell(celldatos);

                celldatos= reportUtil.encabezadoTablaGris("Statement Closing Date");
                celldatos.setBorder(Rectangle.TOP);
                celldatos.setBorderColorTop(new BaseColor(0,0,0));
                TableDatosTarjetaCredito.addCell(celldatos);

                celldatos = new PdfPCell(reportUtil.formatoValor(8,lim_credito));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                TableDatosTarjetaCredito.addCell(celldatos);

                celldatos = new PdfPCell(reportUtil.formatoValor(8,credito_disp));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                TableDatosTarjetaCredito.addCell(celldatos);


                celldatos = new PdfPCell(reportUtil.formatoValor(8,fecha_factura));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                TableDatosTarjetaCredito.addCell(celldatos);

                //el encabezado de las tablas con formato

                celldatos= reportUtil.encabezadoTablaGris("New Balance");
                celldatos.setBorder(Rectangle.TOP);
                celldatos.setBorderColorTop(new BaseColor(0,0,0));
                TableDatosTarjetaCredito.addCell(celldatos);

                celldatos= reportUtil.encabezadoTablaGris("Minimum Payment Due");
                celldatos.setBorder(Rectangle.TOP);
                celldatos.setBorderColorTop(new BaseColor(0,0,0));
                TableDatosTarjetaCredito.addCell(celldatos);

                celldatos= reportUtil.encabezadoTablaGris("Payment Due Date");
                celldatos.setBorder(Rectangle.TOP);
                celldatos.setBorderColorTop(new BaseColor(0,0,0));
                TableDatosTarjetaCredito.addCell(celldatos);

                celldatos = new PdfPCell(reportUtil.formatoValor(8,pag_total));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                TableDatosTarjetaCredito.addCell(celldatos);

                celldatos = new PdfPCell(reportUtil.formatoValor(8,pag_min_mes));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                TableDatosTarjetaCredito.addCell(celldatos);


                celldatos = new PdfPCell(reportUtil.formatoValor(8,fec_pag_antes));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                TableDatosTarjetaCredito.addCell(celldatos);

                // AGREGANDO DATOS DE LA TARJETA DE CREDITO
                celldatos = new PdfPCell(TableDatosTarjetaCredito);
                celldatos.setBorder(Rectangle.NO_BORDER);
                TableCuentaCabecera.addCell(celldatos);



                document.add(TableCuentaCabecera);


                medidaCeldas = new float[]{1.5f};
                PdfPTable TableSeparacion= reportUtil.crearTabla(1,100,medidaCeldas);
                TableSeparacion.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell cellSeparador=new PdfPCell();

                cellSeparador= reportUtil.datosTabla("  ", Element.ALIGN_LEFT);
                TableSeparacion.addCell(cellSeparador);
                document.add(TableSeparacion);

                //se crea tabla balance
                medidaCeldas = new float[]{1f, 0.55f,0.55f,0.55f};
                PdfPTable TableBalance= reportUtil.crearTabla(4,100,medidaCeldas);

                //el encabezado de las tablas con formato

                celldatos= reportUtil.encabezadoTablaGris("Previous Balance");
                celldatos.setBorder(Rectangle.TOP);
                TableBalance.addCell(celldatos);

                celldatos= reportUtil.encabezadoTablaGris("(+) Purchases and Charges");
                celldatos.setBorder(Rectangle.TOP);
                TableBalance.addCell(celldatos);

                celldatos= reportUtil.encabezadoTablaGris("(-) Payments and Credits");
                celldatos.setBorder(Rectangle.TOP);
                TableBalance.addCell(celldatos);

                celldatos= reportUtil.encabezadoTablaGris("New Balance");
                celldatos.setBorder(Rectangle.TOP);
                TableBalance.addCell(celldatos);

                //datos de la tabla de encabezado balance


                celldatos= reportUtil.datosTabla(sal_anterior,Element.ALIGN_CENTER);
                TableBalance.addCell(celldatos);

                celldatos= reportUtil.datosTabla(cargos,Element.ALIGN_CENTER);
                TableBalance.addCell(celldatos);

                celldatos= reportUtil.datosTabla(abonos,Element.ALIGN_CENTER);
                TableBalance.addCell(celldatos);

                celldatos= reportUtil.datosTabla(saldo_actual,Element.ALIGN_CENTER);
                TableBalance.addCell(celldatos);



                document.add(TableBalance);

                //tabla detalles movimientos
                medidaCeldas = new float[]{0.35f,0.75f , 1.0f, 0.55f};
                PdfPTable TableMovimiento= reportUtil.crearTabla(4,100,medidaCeldas);


                //////////Info PL////////////

                List<List<String>> tabla = new ArrayList<List<String>>();
                tabla = reportsPdfServicio.consultarTablaEdoCuentaTDC(num_cta.trim(),codproserv,mes);

                /////////////////////////////

                celldatos =reportUtil.encabezadoTablaGris("Transaction Date");
                celldatos.setBorder(Rectangle.TOP);
                TableMovimiento.addCell(celldatos);

                celldatos =reportUtil.encabezadoTablaGris("Reference Number");
                celldatos.setBorder(Rectangle.TOP);
                TableMovimiento.addCell(celldatos);

                celldatos =reportUtil.encabezadoTablaGris("Transaction Description");
                celldatos.setBorder(Rectangle.TOP);
                TableMovimiento.addCell(celldatos);

                celldatos =reportUtil.encabezadoTablaGris("Amount (USD)");
                celldatos.setBorder(Rectangle.TOP);
                TableMovimiento.addCell(celldatos);



                double saldo              = 0.0;
                double montoMovimiento    = 0.0;
                String primeraDescripcion = "";
                String primerSaldo        = "";
                int pag=1;
                int cantidad=34;
                int pos_tabla=0;
                String account_number="";
                //-------------------------- Movimientos del estado de cuenta ------------------------------------
                for (int i=0;i<tabla.size();i++,pos_tabla++) {
                    if(i==0){
                        saldo=0;
                        celldatos =reportUtil.datosTablaEncabezado("Account Number: "+ tabla.get(i).get(0) ,Element.ALIGN_CENTER);
                        celldatos.setBorder(Rectangle.NO_BORDER);
                        celldatos.setColspan(4);
                        celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                        TableMovimiento.addCell(celldatos);
                        account_number= tabla.get(i).get(0);
                    }
                    //  Se muestran 34 movimientos o transacciones por página
                    if (((i!=0) && ((i%cantidad) == 0)) || (!account_number.equalsIgnoreCase(tabla.get(i).get(0))) ) {
                        if((!account_number.equalsIgnoreCase(tabla.get(i).get(0)))){
                            Double do2= new Double(saldo);
                            DecimalFormat decf = new DecimalFormat("###.##");
                            celldatos = new PdfPCell(reportUtil.formatoValor(9,"TRANSACTIONS SUB-TOTAL.... "+String.valueOf(decf.format(saldo))));
                            celldatos.setBorder(Rectangle.NO_BORDER);
                            celldatos.setColspan(4);
                            celldatos.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            TableMovimiento.addCell(celldatos);

                            document.add(TableMovimiento);
                        }
                        document.newPage();

                        TableMovimiento.deleteBodyRows();
                        celldatos =reportUtil.encabezadoTablaGris("Transaction Date");
                        celldatos.setBorder(Rectangle.TOP);
                        TableMovimiento.addCell(celldatos);

                        celldatos =reportUtil.encabezadoTablaGris("Reference Number");
                        celldatos.setBorder(Rectangle.TOP);
                        TableMovimiento.addCell(celldatos);

                        celldatos =reportUtil.encabezadoTablaGris("Transaction Description");
                        celldatos.setBorder(Rectangle.TOP);
                        TableMovimiento.addCell(celldatos);

                        celldatos =reportUtil.encabezadoTablaGris("Amount (USD)");
                        celldatos.setBorder(Rectangle.TOP);
                        TableMovimiento.addCell(celldatos);



                        float[] medidaCeldas_pag = {5f,5f};
                        PdfPTable TableCuentaCabecera_pag = reportUtil.crearTabla(2,100,medidaCeldas_pag);  // mueestra el Statement of Account y su respectiva imagen
                        TableCuentaCabecera_pag.setHorizontalAlignment(Element.ALIGN_LEFT);

                        imagenCel_logo.setBorder(Rectangle.NO_BORDER);
                        TableCuentaCabecera_pag.addCell(imagenCel_logo);
                        TableCuentaCabecera_pag.addCell(cellStatement_visa);

                        // AGREGANDO DATOS DE LA PERSONA
                        celldatos = new PdfPCell(TableDatosPersona);
                        celldatos.setBorder(Rectangle.NO_BORDER);
                        TableCuentaCabecera_pag.addCell(celldatos);

                        // CREANDO TABLA PARA DATOS DE LA TARJETA DE CREDITO
                        TableDatosTarjetaCredito = new PdfPTable(3);
                        TableDatosTarjetaCredito.setWidthPercentage(100);
                        TableDatosTarjetaCredito.setHorizontalAlignment(Element.ALIGN_CENTER);
                        //el encabezado de las tablas con formato
                        celldatos= reportUtil.encabezadoTablaGris("Account Number");
                        celldatos.setColspan(2);
                        celldatos.setHorizontalAlignment(Element.ALIGN_LEFT);
                        celldatos.setBorder(Rectangle.TOP);
                        celldatos.setBorderColorTop(new BaseColor(0,0,0));
                        TableDatosTarjetaCredito.addCell(celldatos);

                        celldatos= reportUtil.encabezadoTablaGris("Page");
                        celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celldatos.setBorder(Rectangle.TOP);
                        celldatos.setBorderColorTop(new BaseColor(0,0,0));
                        TableDatosTarjetaCredito.addCell(celldatos);

                        celldatos = new PdfPCell(reportUtil.formatoValor(8,num_cta_plast_ppal));
                        celldatos.setColspan(2);
                        celldatos.setBorder(Rectangle.BOTTOM);
                        celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                        TableDatosTarjetaCredito.addCell(celldatos);

                        celldatos = new PdfPCell(reportUtil.formatoValor(8,String.valueOf(++pag)));
                        celldatos.setBorder(Rectangle.BOTTOM);
                        celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                        TableDatosTarjetaCredito.addCell(celldatos);

                        celldatos = new PdfPCell(TableDatosTarjetaCredito);
                        celldatos.setBorder(Rectangle.NO_BORDER);

                        TableCuentaCabecera_pag.addCell(celldatos);

                        document.add(TableCuentaCabecera_pag);
                        document.add(TableSeparacion);
                        document.add(TableSeparacion);
                        document.add(TableSeparacion);

                        if((!account_number.equalsIgnoreCase(tabla.get(i).get(0)))){
                            saldo=0;
                            celldatos =reportUtil.datosTablaEncabezado("Account Number: "+ tabla.get(i).get(0) ,Element.ALIGN_CENTER);
                            celldatos.setBorder(Rectangle.NO_BORDER);
                            celldatos.setColspan(4);
                            celldatos.setHorizontalAlignment(Element.ALIGN_CENTER);
                            TableMovimiento.addCell(celldatos);
                            account_number= tabla.get(i).get(0);
                        }
                    }

                    //------------------- Resto de las lineas de los movimientos del estado de cuenta ---------------------------


                    celldatos =reportUtil.datosTabla_9(tabla.get(i).get(1), Element.ALIGN_CENTER);
                    TableMovimiento.addCell(celldatos);

                    celldatos =reportUtil.datosTabla_9(tabla.get(i).get(2), Element.ALIGN_CENTER);
                    TableMovimiento.addCell(celldatos);

                    celldatos =reportUtil.datosTabla_9(tabla.get(i).get(3), Element.ALIGN_LEFT);
                    TableMovimiento.addCell(celldatos);

                    if(tabla.get(i).get(6).equalsIgnoreCase("CR"))
                        celldatos =reportUtil.datosTabla_9("-"+CurrencyFormatter.formatNumber(tabla.get(i).get(5),2,","), Element.ALIGN_RIGHT);
                    else
                        celldatos =reportUtil.datosTabla_9(CurrencyFormatter.formatNumber(tabla.get(i).get(5),2,","), Element.ALIGN_RIGHT);
                    TableMovimiento.addCell(celldatos);

                    if(tabla.get(i).get(6).equalsIgnoreCase("CR"))
                        montoMovimiento = Double.parseDouble(tabla.get(i).get(5)) * -1;
                    else
                        montoMovimiento = Double.parseDouble(tabla.get(i).get(5));
                    saldo = (saldo + montoMovimiento);


                    document.add(TableMovimiento);
                    TableMovimiento= reportUtil.crearTabla(4,100,medidaCeldas);
                }

                Double do2= new Double(saldo);
                DecimalFormat decf = new DecimalFormat("###.##");
//              celldatos = new PdfPCell(reportUtil.formatoValor(8,"TRANSACTIONS SUB-TOTAL.... "+String.valueOf(decf.format(saldo))));
                celldatos = new PdfPCell(reportUtil.formatoValor(8,"TRANSACTIONS SUB-TOTAL.... "+CurrencyFormatter.formatNumber(saldo,2,",")));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setColspan(4);
                celldatos.setHorizontalAlignment(Element.ALIGN_RIGHT);
                TableMovimiento.addCell(celldatos);
                document.add(TableMovimiento);
                document.close();
                VBTUsersOd usuario=(VBTUsersOd) session.get("user");
                this.getReportsPdfServicio().guardarLog(usuario.getLogin(),"13","2","9",num_cta.trim(),usuario.getIP(),"Generar reportes Tarjeta de credito");

                inputStream  =  new ByteArrayInputStream(buffer.toByteArray());
            }else{
                throw (new Exception ("No Existe",null));
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+ReportsPdfAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            inputStream=crearDocument_error();
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
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

    public IReportsPdfServicio getReportsPdfServicio() {
        return reportsPdfServicio;
    }

    public void setReportsPdfServicio(IReportsPdfServicio reportsPdfServicio) {
        this.reportsPdfServicio = reportsPdfServicio;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getNum_cta() {
        return num_cta;
    }

    public void setNum_cta(String num_cta) {
        this.num_cta = num_cta;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(String fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public String getColocacion() {
        return colocacion;
    }

    public void setColocacion(String colocacion) {
        this.colocacion = colocacion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getFechaValor() {
        return fechaValor;
    }

    public void setFechaValor(String fechaValor) {
        this.fechaValor = fechaValor;
    }

    public String getTasadecambio() {
        return tasadecambio;
    }

    public void setTasadecambio(String tasadecambio) {
        this.tasadecambio = tasadecambio;
    }


    public String getCodproserv() {
        return codproserv;
    }

    public void setCodproserv(String codproserv) {
        this.codproserv = codproserv;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getTasainteres() {
        return tasainteres;
    }

    public void setTasainteres(String tasainteres) {
        this.tasainteres = tasainteres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_operacion() {
        return fecha_operacion;
    }

    public void setFecha_operacion(String fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getTipMov() {
        return tipMov;
    }

    public void setTipMov(String tipMov) {
        this.tipMov = tipMov;
    }

    public InputStream crearDocument_error(){
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        // Modo inicial el reporte
        try{
            // Modo inicial el reporte
            ReportUtil reportUtil = new ReportUtil();
            Document document = reportUtil.createDocument("Report");
            PdfWriter writer = PdfWriter.getInstance(document, buffer);


            // Se asocia eventos al documente header , footer etc
            TableHeader event = new TableHeader();
            writer.setPageEvent(event);
            document.open();

            float[] medidaCeldas = {10f};
            PdfPTable TableCuentaCabecera = reportUtil.crearTabla(1,100,medidaCeldas);  // mueestra el Statement of Account y su respectiva imagen
            TableCuentaCabecera.setHorizontalAlignment(Element.ALIGN_LEFT);

            Paragraph tituloGrande = reportUtil.crearTituloGrande(14,"Error en Reporte ");
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
            TableCuentaCabecera.setHorizontalAlignment(Element.ALIGN_LEFT);

            //PdfPCell  celldatos = new PdfPCell(reportUtil.formatoValor(8,"Ha ocurrido un error generar el PDF, por favor ponerse en contacto con el Banco"));
            PdfPCell  celldatos = new PdfPCell(reportUtil.formatoValor(8,"No existen movimientos para el mes seleccionado."));
            celldatos.setColspan(2);
            celldatos.setBorder(Rectangle.NO_BORDER);
            TableCuentaCabecera2.addCell(celldatos);


            document.add(TableCuentaCabecera2);

            document.close();
        }catch (Exception e) {

        }


        return inputStream  =  new ByteArrayInputStream(buffer.toByteArray());
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

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
            try {
                if(reporte.equalsIgnoreCase("EDC")){
                        piePagina_EC(writer,document);
                }else if(reporte.equalsIgnoreCase("CERTIFICADOS")){
                    piePagina_certificados(writer,document);
                }else if(reporte.equalsIgnoreCase("TDC")){
                    if(writer.getCurrentPageNumber()==1){
                        piePagina_TDC(writer,document);
                    }
                    else{
                        piePagina_TDC2(writer,document);
                    }

                }

            } catch (DocumentException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            //Escribimos el contenido al cerrar el documento
            Phrase frase =new Phrase();
            frase =new Phrase();
            frase.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, com.itextpdf.text.Font.BOLD, BaseColor.BLACK));
            frase.add(new Chunk(String.valueOf(writer.getPageNumber() - 1)));

            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,frase,2, 2, 0);
        }

        public void piePagina_EC(PdfWriter writer, Document document) throws DocumentException {
            try {


                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","VE".toUpperCase()));
                String fechaA = formatoFecha.format(new Date());
                Date fechaActual = null;
                Date fechaDesdeEdoCta= null;
                Date fechaHastaEdoCta=null;

                try {
                    fechaActual = formatoFecha.parse(fechaA);
                    fechaDesdeEdoCta= formatoFecha.parse(ResourceBundle.getBundle("vbtonline").getString("fechaDesdeEdoCta"));
                    fechaHastaEdoCta=formatoFecha.parse(ResourceBundle.getBundle("vbtonline").getString("fechaHastaEdoCta"));
                } catch (Exception ex) {

                }


                String msgPiedePagina1 = "In accordance with the applicable laws of the Cayman Islands and pursuant to a special resolution of the sole shareholder of Venecredit Bank & Trust, Ltd. (the \"Bank\") passed on ";
                String msgPiedePagina2 = "24 February 2012, the name of the Bank changed from \"Venecredit Bank & Trust, Ltd.\" to \"VBT Bank & Trust, Ltd.\"  All terms and conditions relating to the account agreements ";
                String msgPiedePagina3 = "between the Bank and its account holders remain unchanged.";

                String Mensline1="PLEASE REVIEW AND REPORT ANY DISCREPANCIES TO OUR AUDITORS DELOITTE & TOUCHE AT ONE CAPITAL PLAZA, P.O. BOX 1787, GRAND CAYMAN";
                String Mensline2="KY1-1109, CAYMAN ISLANDS. TO THE ATTENTION OF STACEY MATHIS EMAIL: smathis@deloitte.com TEL NUMBER: (345) 814-2277 AND ";
                String Mensline3="FAX NUMBER: (345) 949-8238.";

                String nota = ResourceBundle.getBundle("vbtonline").getString("notaEdcCta");

                String PiedePagina1 = "VBT Bank & Trust, Ltd.";
                String PiedePagina2 = "P.O.Box 454, 4th Floor, Flagship Building, 70 Harbour Drive, George Town, Grand Cayman KY1-1106, CAYMAN ISLANDS";
                String PiedePagina3 = "Phone (345)949-6917. Fax (345)949-8017. e-mail: vib@candw.ky Website: www.vbtbank.com";

                PdfPTable TablePie_pag = new PdfPTable(1);
                PdfPTable Pg = new PdfPTable(2);

                TablePie_pag.setTotalWidth(550);
                TablePie_pag.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                TablePie_pag.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                Pg.setTotalWidth(100);
                Pg.setWidths(new int[]{50, 50});
                Pg.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                Pg.getDefaultCell().setBorder(Rectangle.NO_BORDER);
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

                if (intMes >=9 ) {
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


                String mesAct=Integer.toString(fechaActual.getMonth());
                if (mesAct.length()==1)
                    mesAct="0"+mesAct;

                //if ((fechaActual.after(fechaDesdeEdoCta))&&(fechaActual.before(fechaHastaEdoCta))){
                if (ResourceBundle.getBundle("vbtonline").getString("mesesNotaEdoCta").indexOf (mesAct) != -1){

                //Nota segun el vbtonline properties
                    celldatos= new PdfPCell(reportUtil.formatoValor(7,nota));
                    celldatos.setBorder(Rectangle.TOP);
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
                celldatos.setBorder(Rectangle.BOTTOM);
                celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                TablePie_pag.addCell(celldatos);


                celldatos= new PdfPCell(reportUtil.formatoValor(7,String.format("Page %d of ", writer.getCurrentPageNumber())));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
                celldatos.setVerticalAlignment(Rectangle.ALIGN_BOTTOM);
                //celldatos.setColspan(2);
                Pg.addCell(celldatos);

                celldatos = new PdfPCell(Image.getInstance(total));
                celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                celldatos.setBorder(Rectangle.NO_BORDER);
                //celldatos.setColspan(2);
                Pg.addCell(celldatos);

                TablePie_pag.addCell(Pg);
                TablePie_pag.writeSelectedRows(0, -1, 40, 85, writer.getDirectContent());
            } catch (BadElementException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (DocumentException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        public PdfPTable piePagina_TDC2(PdfWriter writer, Document document) throws DocumentException {
            String msgPiedePagina1 = "In accordance with the applicable laws of the Cayman Islands and pursuant to a special resolution of the sole shareholder of Venecredit Bank & Trust, Ltd. (the \"Bank\") passed on 24 February 2012, the name of the Bank changed from \"Venecredit Bank & Trust, Ltd.\" to \"VBT Bank & Trust, Ltd.\"  All terms and conditions relating to the account agreements between the Bank and its account holders remain unchanged.";
            String msgPiedePagina2 = "If you have questions about your statement, you can either call your Financial Advisor/Account Executive or e-mail clientservices@candw.ky to get in touch directly with VBT Bank & Trust, Ltd.";
            String msgPiedePagina3 = "PLEASE REVIEW AND REPORT ANY DISCREPANCIES TO OUR AUDITORS DELOITTE & TOUCHE AT ONE CAPITAL PLAZA, P.O. BOX 1787 GRAND CAYMAN KY1-1109, CAYMAN ISLANDS. TO THE ATTENTION OF STACEY MATHIS EMAIL: smathis@deloitte.com TEL NUMBER: (345) 814-2277 AND FAX NUMBER: (345)949-8238.";

            PdfPTable TablePie_pag = new PdfPTable(1);
            PdfPTable Pg = new PdfPTable(2);

            TablePie_pag.setTotalWidth(550);
            TablePie_pag.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            TablePie_pag.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            Pg.setTotalWidth(100);
            Pg.setWidths(new int[]{50, 50});
            Pg.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            Pg.getDefaultCell().setBorder(Rectangle.ALIGN_TOP);

            float[] medidaCeldas_pag = {3f,3f,3f,3f};
            ReportUtil reportUtil = new ReportUtil();
            PdfPTable TablePie_pag1 = reportUtil.crearTabla(4,100,medidaCeldas_pag);
            PdfPCell  celldatos;

            celldatos= new PdfPCell(reportUtil.formatoValor(6,msgPiedePagina1));
            celldatos.setBorder(Rectangle.NO_BORDER);
            celldatos.setColspan(4);
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
            TablePie_pag1.addCell(celldatos);

            celldatos= new PdfPCell(reportUtil.formatoValor(6,msgPiedePagina2));
            celldatos.setBorder(Rectangle.NO_BORDER);
            celldatos.setColspan(4);
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
            TablePie_pag1.addCell(celldatos);

            int intMes = 0;
            if (!NullFormatter.isBlank(mes)) {
                intMes = Integer.parseInt(mes.substring(0,2));
            }

            if (intMes >= 9) {
                celldatos= new PdfPCell(reportUtil.formatoValor(6,msgPiedePagina3));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setColspan(4);
                celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                TablePie_pag1.addCell(celldatos);
            }

            TablePie_pag1.addCell(celldatos);

            celldatos= new PdfPCell(reportUtil.formatoValor(7,String.format("Page %d of ", writer.getCurrentPageNumber())));
            celldatos.setBorder(Rectangle.NO_BORDER);
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
            celldatos.setVerticalAlignment(Rectangle.ALIGN_BOTTOM);
            Pg.addCell(celldatos);

            celldatos = new PdfPCell(Image.getInstance(total));
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
            celldatos.setBorder(Rectangle.NO_BORDER);
            Pg.addCell(celldatos);

            TablePie_pag.addCell(TablePie_pag1);
            TablePie_pag.addCell(Pg);
            TablePie_pag.writeSelectedRows(0, -1, 40, 100, writer.getDirectContent());

            return   TablePie_pag;
        }

        public PdfPTable piePagina_TDC(PdfWriter writer, Document document) throws DocumentException {
            String msgPiedePagina0 = "Dear Carholder, As of February 28th, 2012, a flat fee of 2% will be applied to each transaction made with your Visa Gold Creditcard (principal or supplementary) on any currency other than the US Dollar. The resulting fee, calculated upon conversion of amount of the transaction, will be reflected in US Dollars and identified  as \"Foreign Transaction Fee\"  on your statement.";
            String msgPiedePagina1 = "In accordance with the applicable laws of the Cayman Islands and pursuant to a special resolution of the sole shareholder of Venecredit Bank & Trust, Ltd. (the \"Bank\") passed on 24 February 2012, the name of the Bank changed from \"Venecredit Bank & Trust, Ltd.\" to \"VBT Bank & Trust, Ltd.\"  All terms and conditions relating to the account agreements between the Bank and its account holders remain unchanged.";
            String msgPiedePagina2 = "If you have questions about your statement, you can either call your Financial Advisor/Account Executive or e-mail clientservices@candw.ky to get in touch directly with VBT Bank & Trust, Ltd.";
            String msgPiedePagina3 = "PLEASE REVIEW AND REPORT ANY DISCREPANCIES TO OUR AUDITORS DELOITTE & TOUCHE AT ONE CAPITAL PLAZA, P.O. BOX 1787 GRAND CAYMAN KY1-1109, CAYMAN ISLANDS. TO THE ATTENTION OF STACEY MATHIS EMAIL: smathis@deloitte.com TEL NUMBER: (345) 814-2277 AND FAX NUMBER: (345)949-8238.";

            PdfPTable TablePie_pag = new PdfPTable(1);
            PdfPTable Pg = new PdfPTable(2);

            TablePie_pag.setTotalWidth(550);
            TablePie_pag.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            TablePie_pag.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            Pg.setTotalWidth(100);
            Pg.setWidths(new int[]{50, 50});
            Pg.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            Pg.getDefaultCell().setBorder(Rectangle.ALIGN_TOP);

            float[] medidaCeldas_pag = {3f,3f,3f,3f};
            ReportUtil reportUtil = new ReportUtil();
            PdfPTable TablePie_pag1 = reportUtil.crearTabla(4,100,medidaCeldas_pag);
            PdfPCell  celldatos;


            celldatos= reportUtil.encabezadoTablaGris("Interest Rate");
            celldatos.setBorder(Rectangle.TOP);
            TablePie_pag1.addCell(celldatos);

            celldatos= reportUtil.encabezadoTablaGris("Finance Charges");
            celldatos.setBorder(Rectangle.TOP);
            TablePie_pag1.addCell(celldatos);

            celldatos= reportUtil.encabezadoTablaGris("Late Interest Rate");
            celldatos.setBorder(Rectangle.TOP);
            TablePie_pag1.addCell(celldatos);

            celldatos= reportUtil.encabezadoTablaGris("Days in Billing Cycle");
            celldatos.setBorder(Rectangle.TOP);
            TablePie_pag1.addCell(celldatos);

            celldatos= new PdfPCell(reportUtil.formatoValor(8,tas_int));
            celldatos.setBorder(Rectangle.TOP);
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
            TablePie_pag1.addCell(celldatos);

            celldatos= new PdfPCell(reportUtil.formatoValor(8,int_bon));
            celldatos.setBorder(Rectangle.TOP);
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
            TablePie_pag1.addCell(celldatos);

            celldatos= new PdfPCell(reportUtil.formatoValor(8,tasa_mora));
            celldatos.setBorder(Rectangle.TOP);
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
            TablePie_pag1.addCell(celldatos);

            celldatos= new PdfPCell(reportUtil.formatoValor(8,periodo_fact));
            celldatos.setBorder(Rectangle.TOP);
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
            TablePie_pag1.addCell(celldatos);

            celldatos= new PdfPCell(reportUtil.formatoValor(6,msgPiedePagina0));
            celldatos.setBackgroundColor(BaseColor.LIGHT_GRAY);
            celldatos.setColspan(4);
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
            TablePie_pag1.addCell(celldatos);

            celldatos= new PdfPCell(reportUtil.formatoValor(6,msgPiedePagina1));
            celldatos.setBorder(Rectangle.NO_BORDER);
            celldatos.setColspan(4);
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
            TablePie_pag1.addCell(celldatos);

            celldatos= new PdfPCell(reportUtil.formatoValor(6,msgPiedePagina2));
            celldatos.setBorder(Rectangle.NO_BORDER);
            celldatos.setColspan(4);
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
            TablePie_pag1.addCell(celldatos);

            int intMes = 0;
            if (!NullFormatter.isBlank(mes)) {
                intMes = Integer.parseInt(mes.substring(0,2));
            }

            if (intMes >= 9) {
                celldatos= new PdfPCell(reportUtil.formatoValor(6,msgPiedePagina3));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setColspan(4);
                celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                TablePie_pag1.addCell(celldatos);
            }


            celldatos= new PdfPCell(reportUtil.formatoValor(7,String.format("Page %d of ", writer.getCurrentPageNumber())));
            celldatos.setBorder(Rectangle.NO_BORDER);
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
            celldatos.setVerticalAlignment(Rectangle.ALIGN_BOTTOM);
            Pg.addCell(celldatos);

            celldatos = new PdfPCell(Image.getInstance(total));
            celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
            celldatos.setBorder(Rectangle.NO_BORDER);
            Pg.addCell(celldatos);

            TablePie_pag.addCell(TablePie_pag1);
            TablePie_pag.addCell(Pg);
            TablePie_pag.writeSelectedRows(0, -1, 40, 140, writer.getDirectContent());

            return   TablePie_pag;
        }

        public void piePagina_certificados(PdfWriter writer, Document document) throws DocumentException {
            try {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","VE".toUpperCase()));
                String fechaA = formatoFecha.format(new Date());
                Date fechaActual = null;
                Date fechaDesdeEdoCta= null;
                Date fechaHastaEdoCta=null;

                try {
                    fechaActual = formatoFecha.parse(fechaA);
                    fechaDesdeEdoCta= formatoFecha.parse(ResourceBundle.getBundle("vbtonline").getString("fechaDesdeEdoCta"));
                    fechaHastaEdoCta=formatoFecha.parse(ResourceBundle.getBundle("vbtonline").getString("fechaHastaEdoCta"));
                } catch (Exception ex) {

                }

                String nota = ResourceBundle.getBundle("vbtonline").getString("notaEdcCta");

                String msgPiedePagina1 = "THIS CONFIRMATION BEARS NOT SIGNATURE\n\n\n\n";

                String PiedePagina1 = "In accordance with the applicable laws of the Cayman Islands and pursuant to a special resolution of the sole shareholder of Venecredit Bank & Trust, Ltd. (the \"Bank\") passed on 24 February 2012, "+
                        "the name of the Bank changed from \"Venecredit Bank & Trust, Ltd.\" to \"VBT Bank & Trust, Ltd.\"  All terms and conditions relating to the account agreements " +
                        "between the Bank and its account holders remain unchanged.";


                String PiedePagina2 = "PLEASE REVIEW AND REPORT ANY DISCREPANCIES TO OUR AUDITORS DELOITTE & TOUCHE AT ONE CAPITAL PLAZA, P.O. BOX 1787, GRAND CAYMAN \n" +
                        "KY1-1109, CAYMAN ISLANDS. TO THE ATTENTION OF STACEY MATHIS EMAIL: smathis@deloitte.com TEL NUMBER: (345) 814-2277 AND \n" +
                        "FAX NUMBER: (345) 949-8238.";
                PdfPTable TablePie_pag = new PdfPTable(1);
                PdfPTable Pg = new PdfPTable(2);

                TablePie_pag.setTotalWidth(475);
                TablePie_pag.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                TablePie_pag.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                Pg.setTotalWidth(100);
                Pg.setWidths(new int[]{50, 50});
                Pg.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                Pg.getDefaultCell().setBorder(Rectangle.ALIGN_TOP);
                ReportUtil reportUtil = new ReportUtil();
                PdfPCell  celldatos;

                celldatos= new PdfPCell(reportUtil.formatoValor(8,msgPiedePagina1));
                celldatos.setBorder(Rectangle.NO_BORDER);
                celldatos.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
                TablePie_pag.addCell(celldatos);

                String mesAct=Integer.toString(fechaActual.getMonth());
                if (mesAct.length()==1)
                    mesAct="0"+mesAct;

                //if ((fechaActual.after(fechaDesdeEdoCta))&&(fechaActual.before(fechaHastaEdoCta))){
                if (ResourceBundle.getBundle("vbtonline").getString("mesesNotaEdoCta").indexOf (mesAct) != -1){

                    //Nota segun el vbtonline properties
                    celldatos= new PdfPCell(reportUtil.formatoValor(7,nota));
                    celldatos.setBorder(Rectangle.TOP);
                    celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                    TablePie_pag.addCell(celldatos);
                }

                if(fechaValor.split("/")[1].equals("11")||fechaValor.split("/")[1].equals("12")){
                    celldatos= new PdfPCell(reportUtil.formatoValor(7,PiedePagina2));
                    celldatos.setBorder(Rectangle.NO_BORDER);
                    celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                    TablePie_pag.addCell(celldatos);
                }else{
                    celldatos= new PdfPCell(reportUtil.formatoValor(7,PiedePagina1));
                    celldatos.setBorder(Rectangle.NO_BORDER);
                    celldatos.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                    TablePie_pag.addCell(celldatos);
                }

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
                TablePie_pag.writeSelectedRows(0, -1, 70, 110, writer.getDirectContent());
            } catch (BadElementException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (DocumentException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }
}





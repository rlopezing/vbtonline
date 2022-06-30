package ve.com.vbtonline.vista.action.clientInstruction;

import ve.com.vbtonline.servicio.od.ClientInstructionOd;
import ve.com.vbtonline.servicio.od.ContentSelectOd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Document;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 02/10/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ClientInstructionPDF {

    public ByteArrayOutputStream generatePdf(ClientInstructionOd carta, List<String> numeros) throws Exception;
    /*public ByteArrayOutputStream generatePdf(ClientInstructionOd carta, List<String> numeros,java.util.List<ContentSelectOd> motivos) throws Exception;*/
}

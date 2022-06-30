package ve.com.vbtonline.servicio.util;

import com.google.gson.Gson;
import ve.com.vbtonline.servicio.od.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.accounts.IAccountsServicio;
import ve.com.vbtonline.servicio.od.*;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 20/07/12
 * Time: 09:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class validacionUtil {
    private Map session;
    private DataJson data=new DataJson();
    private String jsonDetalleEdoCuenta;

    public String validarCuenta(String carteras, List<ContentSelectOd> cuentas, DataJson data){

        String existe = "NO";
        int i=0;
        while((i<cuentas.size()) && (existe.equalsIgnoreCase("NO"))){
            if(!cuentas.get(i).getValue().equalsIgnoreCase(data.getParametros().get(0))){
                existe = "NO";
            }else{
                if(carteras.indexOf(data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|")+1), data.getParametros().get(0).length()).trim())!=-1)
                    existe = "SI";
                else
                    existe = "NO";
            }
            i++;
        }
        return existe;
    }

    public String validarCuenta2(List<ContentSelectOd> cuentas, String cuenta){

        String existe = "NO";
        int i=0;
        while((i<cuentas.size()) && (existe.equalsIgnoreCase("NO"))){
            if(!cuentas.get(i).getValue().split(" ")[0].trim().equalsIgnoreCase(cuenta)){
                existe = "NO";
            }else{
                existe = "SI";
            }
            i++;
        }
        return existe;
    }

    public String validarCuentaTransfer(String carteras, List<ContentSelectOd> cuentas, String cuenta, String cartera){

        String existe = "NO";
        int i=0;
        while((i<cuentas.size()) && (existe.equalsIgnoreCase("NO"))){
            if(!cuentas.get(i).getValue().substring(0,cuentas.get(i).getValue().indexOf("|")).trim().equalsIgnoreCase(cuenta)){
                existe = "NO";
            }else{
                if(carteras.indexOf(cartera)!=-1)
                    existe = "SI";
                else
                    existe = "NO";
            }
            i++;
        }
        return existe;
    }

    public String validarColocaciones(String carteras, List<ContentSelectOd> colocaciones, DataJson data){

        String existe = "NO";
        int i=0;
        if(!data.getParametros().get(0).equalsIgnoreCase("")) {
            while((i<colocaciones.size()) && (existe.equalsIgnoreCase("NO"))){
                if(!colocaciones.get(i).getValue().equalsIgnoreCase(data.getParametros().get(0))){
                    existe = "NO";
                }else{
                    if(carteras.indexOf(data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|")+1), data.getParametros().get(0).length()).trim())!=-1)
                        existe = "SI";
                    else
                        existe = "NO";
                }
                i++;
            }
        }else{
            existe = "SI";
        }

        return existe;
    }

    public String validarColocacion(List<ContentSelectOd> colocaciones, String colocacion){

        String existe = "NO";
        int i=0;
        if(!colocacion.equalsIgnoreCase("")) {
            while((i<colocaciones.size()) && (existe.equalsIgnoreCase("NO"))){
                if(!colocaciones.get(i).getValue().substring(0, colocaciones.get(i).getValue().indexOf("|")).trim().equalsIgnoreCase(colocacion)){
                    existe = "NO";
                }else{
                    existe = "SI";
                }
                i++;
            }
        }else{
            existe = "NO";
        }

        return existe;
    }

    public String validarTDC(List<ContentSelectOd> tarjetas, String tarjeta){

        String existe = "NO";
        int i=0;
        if(!tarjeta.equalsIgnoreCase("")) {
            while((i<tarjetas.size()) && (existe.equalsIgnoreCase("NO"))){
                if(!tarjetas.get(i).getValue().equalsIgnoreCase(tarjeta)){
                    existe = "NO";
                }else{
                    existe = "SI";
                }
                i++;
            }
        }else{
            existe = "NO";
        }

        return existe;
    }

    public String validarMontosTransferencia(ParametrosPersonalesOd parametrosPersonales, SummaryTransfersToOtherBanksOd resumenTOB, String tipoTransf){

        String valido = "OK";
        int i=0;


        String montoPP = new String();
        String montoPPTO = new String();

        if(tipoTransf.equalsIgnoreCase("TOB")){
             montoPP = parametrosPersonales.getEx_mmtd().replace(".", "");
             montoPPTO = parametrosPersonales.getEx_mmto().replace(".", "");
        }else{
            montoPP = parametrosPersonales.getVbt_mmaxtd().replace(".", "");
            montoPPTO = parametrosPersonales.getVbt_mmto().replace(".", "");
        }


        if((Double.parseDouble(resumenTOB.getAmount()) > Double.parseDouble(montoPPTO.replace(",","."))) || (Double.parseDouble(resumenTOB.getAmount()) > Double.parseDouble(montoPP.replace(",",".")))){
            valido = "NO OK";
        }else{
            valido = "OK";
        }

        return valido;
    }

    public String validarMontosTransferenciaFC(ParametrosPersonalesOd parametrosPersonales, SummaryTransfersToOtherBanksOd resumenTOB, String tipoTransf){

        String valido = "OK";
        int i=0;


        String montoPP = new String();
        String montoPPTO = new String();

        if(tipoTransf.equalsIgnoreCase("TOB")){
             //montoPP = parametrosPersonales.getVbt_mmtd().replace(".", "");
             montoPP = parametrosPersonales.getEx_mmto().replace(".", "");
             montoPPTO = parametrosPersonales.getEx_mmto().replace(".", "");
        }else{
           // montoPP = parametrosPersonales.getVbt_mmtd().replace(".", "");
            montoPP = parametrosPersonales.getEx_mmto().replace(".", "");
            montoPPTO = parametrosPersonales.getEx_mmto().replace(".", "");
        }


        if((Double.parseDouble(resumenTOB.getAmount()) > Double.parseDouble(montoPPTO.replace(",","."))) || (Double.parseDouble(resumenTOB.getAmount()) > Double.parseDouble(montoPP.replace(",",".")))){
            valido = "NO OK";
        }else{
            valido = "OK";
        }

        return valido;
    }


     public long restarFechas(String fechaActual, String fechaAnterior){
         Calendar cal1 = Calendar.getInstance();
         Calendar cal2 = Calendar.getInstance();
         // Establecer las fechas

         String[] fechaAct=fechaActual.split("/");
         String[] fechaAnt=fechaAnterior.split("/");

         cal1.set(Integer.parseInt(fechaAct[2]),  Integer.parseInt(fechaAct[1]), Integer.parseInt(fechaAct[0]));
         cal2.set(Integer.parseInt(fechaAnt[2]), Integer.parseInt(fechaAnt[1]), Integer.parseInt(fechaAnt[0]));

         long milis1 = cal1.getTimeInMillis();

         long milis2 = cal2.getTimeInMillis();

         long diff =  milis1- milis2;

         //long diffSeconds = diff / 1000;

        // long diffMinutes = diff / (60 * 1000);

         long diffHours = diff / (60 * 60 * 1000);

        // long diffDays = diff / (24 * 60 * 60 * 1000);

         return diffHours;
     }



    public long restarFechaDias(String fechaActual, String fechaAnterior){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        // Establecer las fechas

        String[] fechaAct=fechaActual.split("/");
        String[] fechaAnt=fechaAnterior.split("/");

        cal1.set(Integer.parseInt(fechaAct[2]),  Integer.parseInt(fechaAct[1]), Integer.parseInt(fechaAct[0]));
        cal2.set(Integer.parseInt(fechaAnt[2]), Integer.parseInt(fechaAnt[1]), Integer.parseInt(fechaAnt[0]));

        long milis1 = cal1.getTimeInMillis();

        long milis2 = cal2.getTimeInMillis();

        long diff =  milis1- milis2;

        //long diffSeconds = diff / 1000;

        // long diffMinutes = diff / (60 * 1000);

        //long diffHours = diff / (60 * 60 * 1000);

        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffDays;
    }

    public String caracteresEspeciales(String variable){

        variable = variable.replace("Ã¡", "á");
        variable = variable.replace("Ã©", "é");
        variable = variable.replace("Ã*", "í");
        variable = variable.replace("Ã³", "ó");
        variable = variable.replace("Ãº", "ú");
        variable = variable.replace("Ã", "Á");
        variable = variable.replace("Ã‰", "É");
        variable = variable.replace("Ã", "Í");
        variable = variable.replace("Ã­", "í");
        variable = variable.replace("Ã“", "Ó");
        variable = variable.replace("Ãš", "Ú");
        variable = variable.replace("Ã±", "ñ");
        variable = variable.replace("Ã‘", "Ñ");
        return variable;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public DataJson getData() {
        return data;
    }

    public void setData(DataJson data) {
        this.data = data;
    }

    public String getJsonDetalleEdoCuenta() {
        return jsonDetalleEdoCuenta;
    }

    public void setJsonDetalleEdoCuenta(String jsonDetalleEdoCuenta) {
        this.jsonDetalleEdoCuenta = jsonDetalleEdoCuenta;
    }
}

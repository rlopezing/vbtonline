package ve.com.vbtonline.comun.fabrica;


import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: Manuel Serrano
 * Date: 24/05/2010
 * Time: 02:47:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class LanzarFabrica {

    private static LanzarFabrica instancia = new LanzarFabrica();

    public LanzarFabrica() {
    }


    public static void getInstancia (Object clase) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        final String origen = "LanzarFabrica";

        System.out.println("Levantando Contexto "+clase.toString());
        String[] ArregloPaquetes=clase.getClass().getName().toString().split("\\.");
        String Servicio=ArregloPaquetes[ArregloPaquetes.length-1];
        Servicio=(Servicio.indexOf("Action")!=-1)?Servicio.replaceAll("Action", "Servicio"):Servicio.replaceAll("Servicio","Io");
        Servicio=Servicio.substring(0,1).toLowerCase()+Servicio.substring(1);
        String Metodo="set"+Servicio.substring(0,1).toUpperCase()+Servicio.substring(1);
        Object o=((BeanFactory) Fabrica.getInstancia()).getBean(Servicio);
        Object tempClass = clase;
        Class claseCargada = tempClass.getClass();
        Class[] argumentos = new Class[1];
        argumentos[0] = (Servicio.indexOf("Servicio")!=-1)? o.getClass().getInterfaces()[0]:o.getClass();
        Method metodo = claseCargada.getMethod(Metodo,argumentos);
        metodo.invoke(tempClass,o);
        System.out.println("Levantado Contexto "+clase.toString());

        if(Servicio.indexOf("Servicio")!=-1)
            getInstancia(o);



    }


}

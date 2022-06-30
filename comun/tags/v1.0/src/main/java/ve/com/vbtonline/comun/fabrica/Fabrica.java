package ve.com.vbtonline.comun.fabrica;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ResourceBundle;


/**
 * Created by IntelliJ IDEA.
 * User: Manuel Serrano
 * Date: 24/05/2010
 * Time: 01:17:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Fabrica {


    private static Fabrica instancia = new Fabrica();


    public Fabrica() {



    }


    /** Obtiene la Fabrica de Objeto
     * @return La Fabrica de Objetos */
    public static Object getInstancia () {

        BeanFactory factory;
        ApplicationContext ctx;
        ClassPathXmlApplicationContext classPathXmlApplicationContext;
        ctx = new ClassPathXmlApplicationContext(ResourceBundle.getBundle("packagefabrica").getString("beanrefcontextservicio"));
        classPathXmlApplicationContext=(ClassPathXmlApplicationContext)ctx.getBean(ResourceBundle.getBundle("packagefabrica").getString("fabricaservicio"));
        factory = classPathXmlApplicationContext.getBeanFactory();
        return (factory);


    }
}




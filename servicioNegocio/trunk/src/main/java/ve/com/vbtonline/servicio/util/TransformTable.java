package ve.com.vbtonline.servicio.util;

import ve.com.vbtonline.servicio.od.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 20/07/12
 * Time: 09:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class TransformTable {


    public static TableOd getTable(List<String> header,List<List<String>> body){
        TableOd tableOd=new TableOd();
        List<ContentsTableColumnsOd> contenidoTabla_culumnas=new ArrayList<ContentsTableColumnsOd>();

        ContentsTableColumnsOd ctco;
        List<String> lista=new ArrayList<String>();

        for(String h : header)  {
            ctco=new ContentsTableColumnsOd();
            ctco.setSpan_html(h);
            contenidoTabla_culumnas.add(ctco);
        }
        tableOd.setContenidoTabla_culumnas(contenidoTabla_culumnas);

        List<ContentTableInfoOd>  contentTableInfo=new ArrayList<ContentTableInfoOd>();
        List<DataColumnOd> data_culumn=new ArrayList<DataColumnOd>();
        for(int i=0;i<body.size();i++)  {
            ContentTableInfoOd  contentTableInfoOd=new ContentTableInfoOd();
            data_culumn=new ArrayList<DataColumnOd>();
            lista = body.get(i);
            for(int y=0;y<lista.size();y++)  {
                DataColumnOd dco=new DataColumnOd();
                dco.setData_columna(lista.get(y) != null ? lista.get(y).toString() : "");
                data_culumn.add(dco);
            }
            contentTableInfoOd.setData_culumn(data_culumn);
            contentTableInfo.add(contentTableInfoOd);
        }

        tableOd.setContenidoTabla_info(contentTableInfo);

        return tableOd;
    }

    public static TableDetalleOd getTableDetalle(List<String> header,List<List<String>> body){
        TableDetalleOd tableDetalleOd=new TableDetalleOd();
        List<ContentsTableColumnsOd> contenidoTabla_culumnas=new ArrayList<ContentsTableColumnsOd>();

        ContentsTableColumnsOd ctco;
        List<String> lista=new ArrayList<String>();
        List<String> listaNombres=new ArrayList<String>();

        for(String h : header)  {
            ctco=new ContentsTableColumnsOd();
            ctco.setSpan_html(h);
            contenidoTabla_culumnas.add(ctco);
        }
        tableDetalleOd.setContenidoTabla_culumnas(contenidoTabla_culumnas);

        List<ContentTableInfoOd>  contentTableInfo=new ArrayList<ContentTableInfoOd>();
        List<DataColumnOd> data_culumn=new ArrayList<DataColumnOd>();
        for(int i=0;i<body.size();i++)  {
            ContentTableInfoOd  contentTableInfoOd=new ContentTableInfoOd();
            data_culumn=new ArrayList<DataColumnOd>();
            lista = body.get(i);
            for(int y=0;y<lista.size();y++)  {
                DataColumnOd dco=new DataColumnOd();
                dco.setData_columna(lista.get(y).toString());
                data_culumn.add(dco);
            }
            contentTableInfoOd.setData_culumn(data_culumn);
            contentTableInfo.add(contentTableInfoOd);
        }

        tableDetalleOd.setContenidoTabla_info(contentTableInfo);

        return tableDetalleOd;
    }






}

<%@page contentType="text/html" pageEncoding="UTF-8" autoFlush="true"%>
<%@ page import="java.io.*"%>
<%@ page import="java.awt.*"%>
<%@ page import="java.awt.image.*"%>
<%@ page import="javax.imageio.ImageIO"%>
<%@ page import="java.util.*"%>
<%@ page import="ve.com.vbtonline.vista.action.desingBank.DesingBankAction" %>
<%@ page import="java.sql.Blob" %>
<%
    try{

        DesingBankAction as= new DesingBankAction();
        BufferedImage b=as.cargarImagenID(request.getParameter("id"));
        response.setContentType("image/jpeg");
        ServletOutputStream strm = response.getOutputStream();
        ImageIO.write(b,"jpeg",strm);
        b.flush();
        strm.flush();
        strm.close();
        out.clear();
        out = pageContext.pushBody();
    }catch (Exception ex){
        System.out.println(ex.getMessage());
    }
%>
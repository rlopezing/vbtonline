<%@page contentType="text/html" pageEncoding="UTF-8" autoFlush="true"%>
<%@ page import="java.io.*"%>
<%@ page import="java.awt.*"%>
<%@ page import="java.awt.image.*"%>
<%@ page import="javax.imageio.ImageIO"%>
<%@ page import="java.util.*"%>
<%
    try{
        int width=180;
        int height=32;
        String[] select = new String[63];
        java.util.Date date=new java.util.Date();
        java.lang.StringBuffer nbuf = new java.lang.StringBuffer();
        java.util.Random rnum = new java.util.Random(date.getTime());
        select[0]="A";
        select[1]="B";
        select[2]="C";
        select[3]="D";
        select[4]="E";
        select[5]="F";
        select[6]="G";
        select[7]="H";
        select[8]="J";
        select[9]="K";
        select[10]="M";
        select[11]="N";
        select[12]="P";
        select[13]="R";
        select[14]="S";
        select[15]="T";
        select[16]="U";
        select[17]="V";
        select[18]="W";
        select[19]="X";
        select[20]="Y";
        select[21]="Z";
        select[22]="2";
        select[23]="3";
        select[24]="4";
        select[25]="5";
        select[26]="6";
        select[27]="7";
        select[28]="8";
        select[29]="9";
        select[30]="a";
        select[31]="b";
        select[32]="c";
        select[33]="d";
        select[34]="e";
        select[35]="f";
        select[36]="g";
        select[37]="h";
        select[38]="j";
        select[39]="k";
        select[40]="m";
        select[41]="n";
        select[42]="p";
        select[43]="q";
        select[44]="r";
        select[45]="s";
        select[46]="t";
        select[47]="u";
        select[48]="v";
        select[49]="w";
        select[50]="x";
        select[51]="y";
        select[52]="z";

        int i=0, j=0;
        double d = 0;
        //System.out.println("Generating password...");
        do {
            {
                int rands = -1;
                d = rnum.nextDouble();
                rands = new Double(d*51).intValue();
                j = rands+1;
            }
            nbuf.append(select[j]);
            i++;
        } while (i<5);
        Random rdm=new Random();
        int rl=rdm.nextInt();
        String hash1 = Integer.toHexString(rl);
        String capstr=hash1.substring(0,5);
        HttpSession session_actual = request.getSession(true);
        String codigo_generado = (String) session_actual.getAttribute("key");
        if(codigo_generado == null){
            session_actual.setAttribute("key",nbuf.toString());
        }else{
            //session_actual.invalidate();
            HttpSession nueva_session = request.getSession(true);
            nueva_session.setAttribute("key",nbuf.toString());
        }


        Color background = new Color(255,255,255);
        Color fbl = new Color(36, 99, 101);
        Font fnt=new Font("Arial",Font.BOLD,25);
        BufferedImage cpimg =new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = cpimg.createGraphics();
        g.setColor(background);
        g.fillRect(0,0,width,height);
        g.setColor(fbl);
        g.setFont(fnt);
        capstr=nbuf.toString();
       // g.drawString(capstr,1,20);
        String newString = nbuf.toString();
        char[] chars = newString.toCharArray();
        int index = nbuf.length() % 5;
        int x = 25;
        int y = 0;
        Random r = new Random();
       for (int l=0; l<nbuf.length(); l++) {
            x += 18 + ((int)Math.random()*5);
            y =  25 + ((int)Math.random()*5);
            g.drawChars(chars, l, 1, x, y);

        }
        g.setColor(background);
        g.drawLine(50,17,80,17);
        g.drawLine(50,220,80,22);
        response.setContentType("image/jpeg");
        ServletOutputStream strm = response.getOutputStream();
        ImageIO.write(cpimg,"jpeg",strm);
        cpimg.flush();
        strm.flush();
        strm.close();
        out.clear();
        out = pageContext.pushBody();
    }catch (Exception ex){
    }
%>
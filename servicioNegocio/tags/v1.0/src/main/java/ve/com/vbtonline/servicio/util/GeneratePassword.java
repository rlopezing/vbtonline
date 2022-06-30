package ve.com.vbtonline.servicio.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 22/08/2008
 * Time: 04:10:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeneratePassword {

    public GeneratePassword() {
    }

    public String generateRandomPassword() {
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
        } while (i<8);			//change this for size of password
        return nbuf.toString();
    }



}

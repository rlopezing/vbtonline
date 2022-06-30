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
public class ParserUtil {
    private Logger loggerErrorOperacional;
    private ArrayList datePatternList;

    public ArrayList getDatePatternList() {
        return datePatternList;
    }

    public void setDatePatternList(ArrayList datePatternList) {
        this.datePatternList = datePatternList;
    }

    public Logger getLoggerErrorOperacional() {
        return loggerErrorOperacional;
    }

    public void setLoggerErrorOperacional(Logger loggerErrorOperacional) {
        this.loggerErrorOperacional = loggerErrorOperacional;
    }

    public String format(Date date, int patternIndex) {
        return ((SimpleDateFormat) datePatternList.get(patternIndex)).format(date);
    }

    public Date parse(String date, int patternIndex) {
        try {
            return ((SimpleDateFormat) datePatternList.get(patternIndex)).parse(date);
        } catch (ParseException e) {
            if (getLoggerErrorOperacional() != null) {
                getLoggerErrorOperacional().error("ParserUtil.parse: ", e);
            }
            return null;
        }
    }

    public String getPattern(int patternIndex) {
        return ((SimpleDateFormat) datePatternList.get(patternIndex)).toPattern();
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < datePatternList.size(); i++) {
            str.append("Patron ").append(i).append(": ").append(((SimpleDateFormat) datePatternList.get(i)).toPattern()).append("/n");
        }
        return str.toString();
    }

    public Byte[] toArrayByte(byte[] bytes) {
        if (bytes != null) {

            Byte[] objBytes = new Byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                objBytes[i] = new Byte(bytes[i]);
            }

            return (objBytes);

        }

        return null;
    }

    public byte[] toArrayByte(Byte[] bytes) {
        byte[] bytesA = null;
        if (bytes != null) {
            bytesA = new byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                bytesA[i] = bytes[i].byteValue();
            }
        }
        return bytesA;
    }

}

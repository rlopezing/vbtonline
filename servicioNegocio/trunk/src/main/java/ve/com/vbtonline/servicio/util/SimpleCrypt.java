package ve.com.vbtonline.servicio.util;

//import com.venezolano.util.text.TextFormatter;

public class SimpleCrypt {

  public SimpleCrypt()
  {
  }
  
  static byte[] Crypt(String x) throws Exception
  {
     java.security.MessageDigest d =null;
     d = java.security.MessageDigest.getInstance("SHA-1");
     d.reset();
     d.update(x.getBytes());
     return  d.digest();
  }
  
  public String doCrypt(String x) throws Exception
  {
     String result = new String(displayBase64(this.Crypt(x)));
/**     if ((result != null) && (result != ""))
     {
     	result = TextFormatter.replaceString(result,"'","");
     }**/
     return result;
  }
  
  static String displayBase64(byte[] data)
  {
     sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
     String encoded = encoder.encodeBuffer(data);
     return encoded;
  }  
}
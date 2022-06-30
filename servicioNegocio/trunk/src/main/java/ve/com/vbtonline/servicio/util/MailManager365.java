package ve.com.vbtonline.servicio.util;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailManager365 {
    protected String smtpServer;
    protected ResourceBundle configBundle;

    public MailManager365(String configbundle) {
        this.configBundle = ResourceBundle.getBundle(configbundle);
    }

    public void sendPlainMail(String from, String to, String subject, String text) {
        try {
            String SMTPserver = this.configBundle.getString("smtpserver");
            String SMTPuser = this.configBundle.getString("smtpuser");
            String SMTPpass = this.configBundle.getString("smtppass");
            String SMTPport = this.configBundle.getString("smtpport");
            String SMTPfrom = this.configBundle.getString("smtpfrom");
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", SMTPserver);
            //properties.put("mail.smtp.localhost", "venecredit.com");
            properties.put("mail.smtp.starttls.enable", "true"); //Nuevo
            properties.put("mail.smtp.port", SMTPport); //Nuevo
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            Session session = Session.getInstance(properties, (Authenticator)null);
            MimeMessage message = new MimeMessage(session);
            session.setDebug(true);
            //Address fromAddress = new InternetAddress(from);
            Address fromAddress = new InternetAddress(SMTPfrom);
            message.setFrom(fromAddress);
            if (to == null) {
                throw new MessagingException("No \"To\" address specified");
            }

            Address[] toAddress = InternetAddress.parse(to);
            message.setRecipients(RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setText(text);
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(SMTPserver, SMTPuser, SMTPpass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Message sent successfully.");
        } catch (AddressException var13) {
            System.out.println(String.valueOf("Invalid e-mail address.<br>").concat(String.valueOf(var13.getMessage())));
        } catch (SendFailedException var14) {
            System.out.println(String.valueOf("Send failed.<br>").concat(String.valueOf(var14.getMessage())));
        } catch (MessagingException var15) {
            System.out.println(String.valueOf("Unexpected error.<br>").concat(String.valueOf(var15.getMessage())));
        }

    }

    public void sendHtmlMail(String from, String to, String subject, String text) {
        try {
            String SMTPserver = this.configBundle.getString("smtpserver");
            String SMTPuser = this.configBundle.getString("smtpuser");
            String SMTPpass = this.configBundle.getString("smtppass");
            String SMTPport = this.configBundle.getString("smtpport");
            String SMTPfrom = this.configBundle.getString("smtpfrom");
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", SMTPserver);
            //properties.put("mail.smtp.localhost", "venecredit.com");
            properties.put("mail.smtp.starttls.enable", "true"); //Nuevo
            properties.put("mail.smtp.port", SMTPport); //Nuevo
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            Session session = Session.getInstance(properties, (Authenticator)null);
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            //Address fromAddress = new InternetAddress(from);
            Address fromAddress = new InternetAddress(SMTPfrom);
            message.setFrom(fromAddress);
            if (to == null) {
                throw new MessagingException("No \"To\" address specified");
            }

            Address[] toAddress = InternetAddress.parse(to);
            message.setRecipients(RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setContent(text, "text/html");
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(SMTPserver, SMTPuser, SMTPpass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Message sent successfully.");
        } catch (AddressException var13) {
            System.out.println(String.valueOf("Invalid e-mail address.<br>").concat(String.valueOf(var13.getMessage())));
        } catch (SendFailedException var14) {
            System.out.println(String.valueOf("Send failed:").concat(String.valueOf(var14.getMessage())));
        } catch (MessagingException var15) {
            System.out.println(String.valueOf("Unexpected error.<br>").concat(String.valueOf(var15.getMessage())));
        }
    }

    public void sendPlainMailInt(String from, String to, String subject, String text) {
        try {
            String SMTPserver = this.configBundle.getString("smtpserver");
            String SMTPuser = this.configBundle.getString("smtpuser");
            String SMTPpass = this.configBundle.getString("smtppass");
            String SMTPport = this.configBundle.getString("smtpport");
            String SMTPfrom = this.configBundle.getString("smtpfrom");
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", SMTPserver);
            //properties.put("mail.smtp.localhost", "venecredit.com");
            properties.put("mail.smtp.starttls.enable", "true"); //Nuevo
            properties.put("mail.smtp.port", SMTPport); //Nuevo
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            Session session = Session.getInstance(properties, (Authenticator)null);
            MimeMessage message = new MimeMessage(session);
            //Address fromAddress = new InternetAddress(from);
            Address fromAddress = new InternetAddress(SMTPfrom);
            message.setFrom(fromAddress);
            if (to == null) {
                throw new MessagingException("No \"To\" address specified");
            }

            Address[] toAddress = InternetAddress.parse(to);
            message.setRecipients(RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            System.out.println("Message sent successfully.");
        } catch (AddressException var10) {
            System.out.println(String.valueOf("Invalid e-mail address.<br>").concat(String.valueOf(var10.getMessage())));
        } catch (SendFailedException var11) {
            System.out.println(String.valueOf("Send failed.<br>").concat(String.valueOf(var11.getMessage())));
        } catch (MessagingException var12) {
            System.out.println(String.valueOf("Unexpected error.<br>").concat(String.valueOf(var12.getMessage())));
        }

    }

    public void sendHtmlMailInt(String from, String to, String subject, String text) {
        try {
            String SMTPserver = this.configBundle.getString("smtpserver");
            String SMTPuser = this.configBundle.getString("smtpuser");
            String SMTPpass = this.configBundle.getString("smtppass");
            String SMTPport = this.configBundle.getString("smtpport");
            String SMTPfrom = this.configBundle.getString("smtpfrom");
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", SMTPserver);
            //properties.put("mail.smtp.localhost", "venecredit.com");
            properties.put("mail.smtp.starttls.enable", "true"); //Nuevo
            properties.put("mail.smtp.port", SMTPport); //Nuevo
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            Session session = Session.getInstance(properties, (Authenticator)null);
            MimeMessage message = new MimeMessage(session);
            //Address fromAddress = new InternetAddress(from);
            Address fromAddress = new InternetAddress(SMTPfrom);
            message.setFrom(fromAddress);
            if (to == null) {
                throw new MessagingException("No \"To\" address specified");
            }

            Address[] toAddress = InternetAddress.parse(to);
            message.setRecipients(RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setContent(text, "text/html");
            Transport.send(message);
            System.out.println("Message sent successfully.");
        } catch (AddressException var10) {
            System.out.println(String.valueOf("Invalid e-mail address.<br>").concat(String.valueOf(var10.getMessage())));
        } catch (SendFailedException var11) {
            System.out.println(String.valueOf("Send failed.<br>").concat(String.valueOf(var11.getMessage())));
        } catch (MessagingException var12) {
            System.out.println(String.valueOf("Unexpected error.<br>").concat(String.valueOf(var12.getMessage())));
        }

    }
}
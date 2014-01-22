package mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * @author Nguyen Thanh Nhan
 */
public class SpamMailBot {

    private static Authenticator pa;
    private static final String botMail = "";
    private static final String botPassword = "";
    private static Properties props;

    private static void login() {
        String smtpServer = "smtp.gmail.com";
        props = System.getProperties();
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        pa = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(botMail, botPassword);
            }
        };
    }

    public static void sendMail(String recipient, String subject, String message) {
        try {
        if (pa == null) login();
        Session session = Session.getInstance(props, pa);
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(botMail));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipient, false));

        // Noi dung thu
        msg.setSubject(subject);
        msg.setText(message);
        msg.saveChanges();

        // Gui thu
        Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package max.costa.reto1.services;

import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;
import static max.costa.reto1.constant.EmailConstant.CC_EMAIL;
import static max.costa.reto1.constant.EmailConstant.DEFAULT_PORT;
import static max.costa.reto1.constant.EmailConstant.EMAIL_SUBJECT;
import static max.costa.reto1.constant.EmailConstant.FROM_EMAIL;
import static max.costa.reto1.constant.EmailConstant.GMAIL_SMTP_SERVER;
import static max.costa.reto1.constant.EmailConstant.PASSWORD;
import static max.costa.reto1.constant.EmailConstant.SIMPLE_MAIL_TRANSFER_PROTOCOL;
import static max.costa.reto1.constant.EmailConstant.SMTP_AUTH;
import static max.costa.reto1.constant.EmailConstant.SMTP_HOST;
import static max.costa.reto1.constant.EmailConstant.SMTP_PORT;
import static max.costa.reto1.constant.EmailConstant.SMTP_STARTTLS_ENABLE;
import static max.costa.reto1.constant.EmailConstant.SMTP_STARTTLS_REQUIRED;
import static max.costa.reto1.constant.EmailConstant.USERNAME;

import java.text.ParseException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import max.costa.reto1.utility.Utility;

@Service
public class EmailService {

    private Utility utility;

    @Autowired
    public EmailService (Utility utility){
        this.utility = utility;
    }

    public void sendNewPasswordEmail(String firstName, String password, String email) throws MessagingException, ParseException {
        Message message = createEmail(firstName, password, email);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }

    private Message createEmail(String firstName, String password, String email) throws MessagingException, ParseException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(email, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(EMAIL_SUBJECT);
        message.setText("Hola " + firstName + ", \n\nTu nueva contraseña generada es: " + password + "\n\nHospital App");
        message.setSentDate(this.utility.obtenerFechaActual());
        message.saveChanges();
        return message;
    }

    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
        properties.put(SMTP_AUTH, true);
        properties.put(SMTP_PORT, DEFAULT_PORT);
        properties.put(SMTP_STARTTLS_ENABLE, true);
        properties.put(SMTP_STARTTLS_REQUIRED, true);
        return Session.getInstance(properties, null);
    }
}

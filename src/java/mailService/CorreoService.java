package mailService;

import entidades.variables_entorno_correo.VariableEntornoCorreo;
import entidades.variables_entorno_correo.VariableEntornoCorreoFacade;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class CorreoService {

    @EJB
    VariableEntornoCorreoFacade variableEntornoCorreoFacade;

    public void enviarNotificacion(List<String> to, String subject, String body) {
        Properties properties;
        Session session = null;
        VariableEntornoCorreo variableEntornoCorreo;
        variableEntornoCorreo = variableEntornoCorreoFacade.getActive();
        if (variableEntornoCorreo != null) {
            properties = System.getProperties();
            // Setup mail server
            properties.put("mail.smtp.starttls.enable", "true");
            System.out.println(variableEntornoCorreo.getCorreo_from()+variableEntornoCorreo.getCorreo_pass());
            properties.put("mail.smtp.host", variableEntornoCorreo.getCorreo_host());
            properties.put("mail.smtp.user", variableEntornoCorreo.getCorreo_from());
            properties.put("mail.smtp.password", variableEntornoCorreo.getCorreo_pass());
            properties.put("mail.smtp.port", variableEntornoCorreo.getCorreo_port());
            properties.put("mail.smtp.auth", "true");
            //properties.put("mail.smtp.ssl.enable", "true");
            session = Session.getDefaultInstance(properties);
        }

        System.out.println("Enviando Mensaje");
        if (variableEntornoCorreo != null && session != null) {
            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(variableEntornoCorreo.getCorreo_from()));

                // Set To: header field of the header.
                for (String to1 : to) {
                    message.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(to1));
                }

                // Set Subject: header field
                message.setSubject(subject);

                // Now set the actual message
                message.setContent(body, "text/html; charset=utf-8");
                //message.setText(body);           

                // Send message
                Transport transport = session.getTransport("smtp");
                transport.connect(variableEntornoCorreo.getCorreo_host(),
                        variableEntornoCorreo.getCorreo_from(),
                        variableEntornoCorreo.getCorreo_pass());
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                System.out.println("Sent message successfully....");
            } catch (MessagingException mex) {
                util.Utilidades.imprimir_msg("Error", "No se pudo enviar el mensaje");
                mex.printStackTrace();
            } catch (Exception e) {
                util.Utilidades.imprimir_msg("Error", "No se pudo enviar el mensaje");
                e.printStackTrace();
            }
        } else {
            util.Utilidades.imprimir_msg("Error", "No hay información de correos");
        }
    }

}

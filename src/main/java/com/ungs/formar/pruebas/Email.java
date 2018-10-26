package com.ungs.formar.pruebas;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	public static void mail() {
		try {
			Properties props = new Properties();

			// Nombre del host de correo, es smtp.gmail.com
			props.setProperty("mail.smtp.host", "smtp.gmail.com");

			// TLS si est� disponible
			props.setProperty("mail.smtp.starttls.enable", "true");

			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port","587");

			// Nombre del usuario
			//props.setProperty("mail.smtp.user", "gfgrillo3@gmail.com");

			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");

			// Get session
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);
			
			// Define message
			MimeMessage message = new MimeMessage(session);
			//message.setFrom(new InternetAddress("gfgrillo3@gmail.com"));
			message.setSubject("Generar contrase�a");
			//EMAIL Destinatario
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("benescence@hotmail.com"));
			message.setText("Prueba envio mail");
			// Envia el mensaje
			Transport t = session.getTransport("smtp");
			//Email de formar, Contrasena de formar
			t.connect("formarungs@gmail.com","formar123");
			t.sendMessage(message,message.getAllRecipients());
			t.close();

		} catch (Throwable e) {
			System.out.println("Fallo sendEmail al enviar Correo: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

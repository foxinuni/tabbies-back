package dev.downloadablefox.tabbies.webserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.downloadablefox.tabbies.webserver.dtos.ContactMessageDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public ResponseEntity<String> sendContactEmail(@RequestBody ContactMessageDTO message) {
        System.out.println("Petición recibida de: " + message.getEmail());
        try {
            MimeMessage adminMessage = mailSender.createMimeMessage();
            MimeMessageHelper adminHelper = new MimeMessageHelper(adminMessage, true, "UTF-8");
            adminHelper.setTo("tabbies.contacto@gmail.com");
            adminHelper.setSubject("Nuevo mensaje de contacto de " + message.getName());

            String htmlAdmin = "<div style='font-family:Arial,sans-serif;'>" +
                "<h2 style='color:#fbacac;'>🐾 Tabbies - Nuevo Mensaje de Contacto</h2>" +
                "<p><strong>Nombre:</strong> " + message.getName() + "</p>" +
                "<p><strong>Correo:</strong> " + message.getEmail() + "</p>" +
                "<p><strong>Mensaje:</strong><br/>" + message.getMessage() + "</p>" +
                "<hr><p style='font-size:0.8em;color:#999;'>Este correo fue generado automáticamente por el sistema de contacto de Tabbies.</p>" +
                "</div>";

            adminHelper.setText(htmlAdmin, true);
            mailSender.send(adminMessage);
            System.out.println("✅ Correo enviado a Tabbies");

            try {
                MimeMessage userMessage = mailSender.createMimeMessage();
                MimeMessageHelper userHelper = new MimeMessageHelper(userMessage, true, "UTF-8");
                userHelper.setTo(message.getEmail());
                userHelper.setFrom("tabbies.contacto@gmail.com");
                userHelper.setReplyTo("tabbies.contacto@gmail.com");
                userHelper.setSubject("¡Gracias por contactarnos en Tabbies!");

                String htmlUser = "<div style='font-family:Arial,sans-serif; max-width:600px; margin:auto;'>" +
                    "<img src='https://tabbies.com/assets/banner-contacto.jpg' alt='Bienvenido a Tabbies' style='width:100%; border-radius:10px;' />" +
                    "<h2 style='color:#fbacac; text-align:center;'> Gracias por tu mensaje, " + message.getName() + "!</h2>" +
                    "<p style='font-size:16px;'>Tu mensaje ha sido recibido por nuestro equipo. Nos emociona saber de ti</p>" +
                    "<p style='font-size:16px;'>Mientras tanto, puedes explorar todo lo que Tabbies tiene para ofrecer:</p>" +
                    "<div style='text-align:center; margin:20px 0;'>" +
                        "<a href='https://tabbies.com#services' style='display:inline-block; padding:12px 24px; background-color:#fbacac; color:white; text-decoration:none; border-radius:8px; font-weight:bold;'>Ver Servicios</a>" +
                    "</div>" +
                    "<p style='font-size:14px; color:#666;'>Este es un correo automático de confirmación. Gracias por confiar en nosotros 🐾</p>" +
                    "</div>";

                userHelper.setText(htmlUser, true);
                mailSender.send(userMessage);
                System.out.println("Correo enviado al usuario: " + message.getEmail());
            } catch (MessagingException e) {
                System.err.println("Error al enviar correo al usuario: " + e.getMessage());
            }

            return ResponseEntity.ok("Mensaje enviado con éxito");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error al enviar el mensaje a Tabbies: " + e.getMessage());
        }
    }

}


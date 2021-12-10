package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.repositorios.ProductoRepository;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class NegocioApplication{

    public static void main(String[] args) {

        SpringApplication.run(NegocioApplication.class,args);
    }


}

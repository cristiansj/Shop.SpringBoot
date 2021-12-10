package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}

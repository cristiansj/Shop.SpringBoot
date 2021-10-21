package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Persona;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersistenciaApplication {
    public static void main(String[] args) {

      //  SpringApplication.run(PersistenciaApplication.class,args);
        Categoria categoria = new Categoria(1,"Tecnologia");
        System.out.println(categoria.getCodigo());
    }
}

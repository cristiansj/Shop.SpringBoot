package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    @Autowired
    private AdministradorRepository administradorRepository;

    /*
    Prueba para revisar que funcione adecuadamente el registrar
     */
    @Test
    public void registrarTest(){
        Administrador administrador = new Administrador(1,"Cristian","cristian@email.com","123");

        Administrador guardado = administradorRepository.save(administrador);
        Assertions.assertNotNull(guardado);
    }

    /*
    Prueba para revisar que funcione adecuadamente el
     */
    @Test
    @Sql("classpath:administrador.sql")
    public void eliminarTest(){
        administradorRepository.deleteById(1);
        Administrador usuarioBuscado = administradorRepository.findById(1).orElse(null);
        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    @Sql("classpath:administrador.sql")
    public void actualizarTest(){
        Administrador usuarioGuardado = administradorRepository.findById(1).orElse(null);
        usuarioGuardado.setNombre("Dario");
        administradorRepository.save(usuarioGuardado);

        Administrador usuarioBuscado = administradorRepository.findById(1).orElse(null);
        Assertions.assertEquals("Dario",usuarioBuscado.getNombre());
    }

    @Test
    @Sql("classpath:administrador.sql")
    public void ListarTest(){
        List<Administrador> administradores = administradorRepository.findAll();
        administradores.forEach(u -> System.out.println(u));
    }
}

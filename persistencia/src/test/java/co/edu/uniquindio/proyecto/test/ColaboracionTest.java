package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ChatRepository;
import co.edu.uniquindio.proyecto.repositorios.ColaboracionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ColaboracionTest {

    @Autowired
    private ColaboracionRepository colaboracionRepository;

    @Test
    public void registrarTest(){
        Colaboracion colaboracion = new Colaboracion(1);

        Colaboracion colaboracionG = colaboracionRepository.save(colaboracion);
        Assertions.assertNotNull(colaboracionG);
    }

    @Test
    @Sql("classpath:colaboracion.sql")
    public void eliminarTest(){
        colaboracionRepository.deleteById(1);
        Colaboracion colaboracionBuscada = colaboracionRepository.findById(1).orElse(null);
        Assertions.assertNull(colaboracionBuscada);
    }

    @Test
    @Sql("classpath:colaboracion.sql")
    public void actualizarTest(){
        Colaboracion colaboracionG = colaboracionRepository.findById(1).orElse(null);
        colaboracionG.setCodigo(4);
        colaboracionRepository.save(colaboracionG);

        Colaboracion colaboracionB = colaboracionRepository.findById(1).orElse(null);
        Assertions.assertEquals(4,colaboracionB.getCodigo());
    }

    @Test
    @Sql("classpath:colaboracion.sql")
    public void ListarTest(){
        List<Colaboracion> colaboraciones = colaboracionRepository.findAll();
        colaboraciones.forEach(u -> System.out.println(u));
    }
}

package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepository;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaUsuarioTest {

    @Autowired
    private SubastaUsuarioRepository subastaUsuarioRepository;

    @Test
    public void registrarTest(){
        SubastaUsuario subastaUsuario = new SubastaUsuario(1,1542, LocalDateTime.now());

        SubastaUsuario subastaUsuarioG = subastaUsuarioRepository.save(subastaUsuario);
        Assertions.assertNotNull(subastaUsuarioG);
    }

    @Test
    @Sql("classpath:subastaUsuario.sql")
    public void eliminarTest(){
        subastaUsuarioRepository.deleteById(1);
        SubastaUsuario subastaUsuarioBuscado = subastaUsuarioRepository.findById(1).orElse(null);
        Assertions.assertNull(subastaUsuarioBuscado);
    }

    @Test
    @Sql("classpath:subastaUsuario.sql")
    public void actualizarTest(){
        SubastaUsuario subastaUsuarioG = subastaUsuarioRepository.findById(1).orElse(null);
        subastaUsuarioG.setCodigo(4);
        subastaUsuarioRepository.save(subastaUsuarioG);

        SubastaUsuario subastaUsuarioBuscado = subastaUsuarioRepository.findById(1).orElse(null);
        Assertions.assertEquals(4,subastaUsuarioBuscado.getCodigo());
    }

    @Test
    @Sql("classpath:subastaUsuario.sql")
    public void ListarTest(){
        List<SubastaUsuario> subastaUsuarios = subastaUsuarioRepository.findAll();
        subastaUsuarios.forEach(u -> System.out.println(u));
    }
}

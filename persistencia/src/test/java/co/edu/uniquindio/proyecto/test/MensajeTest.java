package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.repositorios.ChatRepository;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepository;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepository;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private

    @Autowired
    private ChatRepository chatRepository;

    @Test
    public void registrarTest(){

        Chat chat = new Chat(1);

        Mensaje mensaje = new Mensaje(1,"hola mundo","cristian", LocalDateTime.now(), 1);

        Mensaje mensajeG = mensajeRepository.save(mensaje);
        Assertions.assertNotNull(mensajeG);
    }

    @Test
    @Sql("classpath:mensaje.sql")
    public void eliminarTest(){
        mensajeRepository.deleteById(1);
        Mensaje mensajeBuscado = mensajeRepository.findById(1).orElse(null);
        Assertions.assertNull(mensajeBuscado);
    }

    @Test
    @Sql("classpath:mensaje.sql")
    public void actualizarTest(){
        Mensaje mensajeG = mensajeRepository.findById(1).orElse(null);
        mensajeG.setCodigo(4);
        mensajeRepository.save(mensajeG);

        Mensaje mensajeBuscado = mensajeRepository.findById(1).orElse(null);
        Assertions.assertEquals(4,mensajeBuscado.getCodigo());
    }

    @Test
    @Sql("classpath:mensaje.sql")
    public void ListarTest(){
        List<Mensaje> mensajes = mensajeRepository.findAll();
        mensajes.forEach(u -> System.out.println(u));
    }
}

package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepository;
import co.edu.uniquindio.proyecto.repositorios.ChatRepository;
import org.assertj.core.internal.bytebuddy.asm.Advice;
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
public class ChatTest {

    @Autowired
    private ChatRepository chatRepository;

    @Test
    public void registrarTest(){
        Chat chat = new Chat(1);

        Chat chatG = chatRepository.save(chat);
        Assertions.assertNotNull(chatG);
    }

    @Test
    @Sql("classpath:chat.sql")
    public void eliminarTest(){
        chatRepository.deleteById(1);
        Chat chatBuscado = chatRepository.findById(1).orElse(null);
        Assertions.assertNull(chatBuscado);
    }

    @Test
    @Sql("classpath:chat.sql")
    public void actualizarTest(){
        Chat chatG = chatRepository.findById(1).orElse(null);
        chatG.setCodigo(4);
        chatRepository.save(chatG);

        Chat chatBuscado = chatRepository.findById(1).orElse(null);
        Assertions.assertEquals(4,chatBuscado.getCodigo());
    }

    @Test
    @Sql("classpath:chat.sql")
    public void ListarTest(){
        List<Chat> chats = chatRepository.findAll();
        chats.forEach(u -> System.out.println(u));
    }
}

package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepository;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void registrarTest(){
        Usuario usuario = new Usuario(1,"Tatiana","tatiana@email.com","123",null);

        Usuario usuarioG = usuarioRepository.save(usuario);
        Assertions.assertNotNull(usuarioG);
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void eliminarTest(){
        usuarioRepository.deleteById(1);
        Usuario usuarioBuscado = usuarioRepository.findById(1).orElse(null);
        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void actualizarTest(){
        Usuario usuarioG = usuarioRepository.findById(1).orElse(null);
        usuarioG.setCodigo(4);
        usuarioRepository.save(usuarioG);

        Usuario usuarioBuscado = usuarioRepository.findById(1).orElse(null);
        Assertions.assertEquals(4,usuarioBuscado.getCodigo());
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void ListarTest(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        usuarios.forEach(u -> System.out.println(u));
    }
}

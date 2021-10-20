package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.EnumGenero;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Temporal;
import java.time.LocalDate;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void registrarTest(){
        Ciudad ciudad = new Ciudad("Armenia");
        ciudadRepo.save(ciudad);

        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("casa","98745994");
        telefonos.put("celular","3122334767");
        Usuario usuario = new Usuario("123","pepito",LocalDate.now(),EnumGenero.MASCULINO,"pepe@email.com",telefonos, ciudad);

        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);
    }

    @Test
    public void eliminarTest(){
        Ciudad ciudad = new Ciudad("Armenia");
        ciudadRepo.save(ciudad);

        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("casa","98745994");
        telefonos.put("celular","3122334767");
        Usuario usuario = new Usuario("123","pepito",LocalDate.now(),EnumGenero.MASCULINO,"pepe@email.com",telefonos, ciudad);

        usuarioRepo.save(usuario);

        usuarioRepo.deleteById("123");
        Usuario usuarioBuscado = usuarioRepo.findById("123").orElse(null);
        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    public void actualizarTest(){
        Ciudad ciudad = new Ciudad("Armenia");
        ciudadRepo.save(ciudad);

        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("casa","98745994");
        telefonos.put("celular","3122334767");
        Usuario usuario = new Usuario("123","pepito",LocalDate.now(),EnumGenero.MASCULINO,"pepe@email.com",telefonos, ciudad);

        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        usuarioGuardado.setEmail("pepito@email.com");

        usuarioRepo.save(usuarioGuardado);

        Usuario usuarioBuscado = usuarioRepo.findById("123").orElse(null);
        Assertions.assertEquals("pepito@email.com",usuarioBuscado.getEmail());
    }

    @Test
    public void ListarTest(){
        Ciudad ciudad = new Ciudad("Armenia");
        ciudadRepo.save(ciudad);

        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("casa","98745994");
        telefonos.put("celular","3122334767");
        Usuario usuario = new Usuario("123","pepito",LocalDate.now(),EnumGenero.MASCULINO,"pepe@email.com",telefonos, ciudad);
        usuarioRepo.save(usuario);
        List<Usuario> usuarios = usuarioRepo.findAll();

        System.out.println(usuarios);
    }
}

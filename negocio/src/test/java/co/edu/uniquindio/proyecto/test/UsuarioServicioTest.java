package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarTest(){

        Usuario user = new Usuario(1, "Pepe", "pepito", "Pepe123@email.com", "1234", null);

        try{
            Usuario respuesta = usuarioServicio.registrarUsuario(user);
            Assertions.assertNotNull(respuesta);
        }catch (Exception e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void eliminarTest(){
        try {
            usuarioServicio.eliminarUsuario(1);
            Assertions.assertTrue(true);
        }catch (Exception e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void listarTest() throws Exception{

        Usuario user1 = new Usuario(1, "Pepe", "pepito", "Pepe123@email.com", "1234", null);
        Usuario user2 = new Usuario(2, "Peppe", "pepitto", "Pepe124@email.com", "1234",null);

        usuarioServicio.registrarUsuario(user1);
        usuarioServicio.registrarUsuario(user2);

        List<Usuario> lista = usuarioServicio.listarUsuarios();

        lista.forEach(System.out::println);
    }

    @Test
    public void actualizarTest() throws Exception{

        Usuario user1 = new Usuario(1, "Pepe", "pepito", "Pepe123@email.com", "1234", null);
        usuarioServicio.registrarUsuario(user1);

        try{
            Usuario buscado = usuarioServicio.obtenerUsuario(1);
            buscado.setEmail("Pepe124@email.com");
            Usuario respuesta = usuarioServicio.actualizarUsuario(buscado);
            Assertions.assertEquals("Pepe124@email.com", respuesta.getEmail());
        }catch (Exception e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void loginTest() throws Exception{

        Usuario user1 = new Usuario(1, "Pepe", "pepito", "Pepe123@email.com", "1234", null);

        usuarioServicio.registrarUsuario(user1);

        try {
            Usuario buscado = usuarioServicio.hacerLogin("Pepe123@email.com", "1234");
            Assertions.assertNotNull(buscado);
        }catch (Exception e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

    }
}

package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepository;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import net.bytebuddy.dynamic.loading.ClassInjector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Test
    public void registrarTest(){

        Ciudad ciudad = new Ciudad(1, "Medellín");
        ciudadRepository.save(ciudad);

        HashMap<String, String> numTelefonos = new HashMap<String, String>();
        numTelefonos.put("oficina","75435467");

        Usuario user = new Usuario(1, "Pepe", "pepito", "Pepe123@email.com", "1234", ciudad, numTelefonos);

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

        Ciudad ciudad = new Ciudad(1, "Manizales");
        ciudadRepository.save(ciudad);

        HashMap<String,String> numTelefonos1 = new HashMap<String,String>();
        HashMap<String,String> numTelefonos2 = new HashMap<String,String>();

        numTelefonos1.put("Casa","3122456543");
        numTelefonos2.put("Oficina","74325412");

        Usuario user1 = new Usuario(1, "Pepe", "pepito", "Pepe123@email.com", "1234", ciudad, numTelefonos1);
        Usuario user2 = new Usuario(2, "Peppe", "pepitto", "Pepe124@email.com", "1234", ciudad, numTelefonos2);

        usuarioServicio.registrarUsuario(user1);
        usuarioServicio.registrarUsuario(user2);

        List<Usuario> lista = usuarioServicio.listarUsuarios();

        lista.forEach(System.out::println);
    }

    @Test
    public void actualizarTest() throws Exception{

        Ciudad ciudad = new Ciudad(1, "Armenia");
        ciudadRepository.save(ciudad);

        HashMap<String,String> numTelefonos = new HashMap<String, String>();
        numTelefonos.put("Personal","3132355729");
        Usuario user1 = new Usuario(1, "Pepe", "pepito", "Pepe123@email.com", "1234", ciudad, numTelefonos);
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

        Ciudad ciudad = new Ciudad(1, "Cali");
        ciudadRepository.save(ciudad);

        HashMap<String,String> numTelefonos = new HashMap<String, String>();
        numTelefonos.put("Personal","3132355729");

        Usuario user1 = new Usuario(1, "Pepe", "pepito", "Pepe123@email.com", "1234", ciudad, numTelefonos);
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

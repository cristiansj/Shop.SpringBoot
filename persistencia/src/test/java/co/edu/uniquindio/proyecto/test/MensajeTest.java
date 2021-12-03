package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    private ProductoRepository productoRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void registrarTest(){

        Ciudad ciudad = new Ciudad(1, "Cali");
        ciudadRepository.save(ciudad);

        HashMap<String, String> numTelefonos = new HashMap<String, String>();
        numTelefonos.put("Casa","3124536789");

        Usuario usuario = new Usuario(1, "Pepe", "pepito123", "pepito123@gmail.com", "1452", ciudad, numTelefonos);
        usuarioRepository.save(usuario);

        LocalDateTime ldt = LocalDateTime.of(2022, 12, 11, 22, 11, 35);
        Categoria categoria1 = new Categoria(1, "Ropa");
        ArrayList<Categoria> categorias= new ArrayList<Categoria>();
        categoriaRepository.save(categoria1);
        categorias.add(categoria1);
        ArrayList<String> imagenes = new ArrayList<String>();
        Producto producto = new Producto(1, "Camiseta", 22, "Camiseta negra",imagenes, 25000D, ldt, usuario, ciudad, categorias);
        productoRepository.save(producto);

        Chat chat = new Chat(1, usuario, producto);
        chatRepository.save(chat);

        Mensaje mensaje = new Mensaje(1,"hola mundo","cristian", LocalDateTime.now(), chat);

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

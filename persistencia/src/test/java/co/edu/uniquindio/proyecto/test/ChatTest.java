package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private  CategoriaRepository categoriaRepository;

    @Test
    public void registrarTest(){

        Ciudad ciudad = new Ciudad("Sanaa");
        ciudadRepository.save(ciudad);

        HashMap<String, String> numTelefonos = new HashMap<String, String>();
        numTelefonos.put("Casa","3145324378");

        Usuario usuario = new Usuario("Pepe", "pepito123", "pepe123@gmail.com", "1423", ciudad, numTelefonos);
        usuarioRepository.save(usuario);

        LocalDateTime ldt = LocalDateTime.of(2022, 11, 12, 6, 15, 59);
        Categoria categoria1 = new Categoria("Ropa");
        ArrayList<Categoria> categorias= new ArrayList<Categoria>();
        categoriaRepository.save(categoria1);
        categorias.add(categoria1);
        ArrayList<String> imagenes = new ArrayList<String>();
        Producto producto = new Producto("Camiseta", 22, "Camiseta negra",imagenes, 25000D, ldt, usuario, ciudad, categorias);
        productoRepository.save(producto);

        Chat chat = new Chat(usuario, producto);

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

    @Test
    @Sql("classpath:chat.sql")
    public void ListarChatsDeVendedor() {
        List<Chat> respuesta = chatRepository.listarChatsDeVendedor(3);
        respuesta.forEach(chat -> System.out.println("el vendedor es ------------------->"+chat.getCodigoProducto().getCodigoVendedor().getCodigo()));
    }

}

package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
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
public class SubastaUsuarioTest {

    @Autowired
    private SubastaUsuarioRepository subastaUsuarioRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SubastaRepository subastaRepository;

    @Test
    public void registrarTest(){

        //Creo una subasta y la guardo.

        Ciudad ciudad = new Ciudad(1, "Medallon");
        ciudadRepository.save(ciudad);

        HashMap<String, String> numTelefonos = new HashMap<String, String>();
        numTelefonos.put("oficina","75435678");

        Usuario usuario = new Usuario(1, "Pepe", "pepito123", "pepito123@gmail.com", "1245", ciudad, numTelefonos);
        usuarioRepository.save(usuario);

        LocalDateTime ldt = LocalDateTime.of(2022, 02, 10, 19, 59, 59);

        Categoria categoria1 = new Categoria(1, "Ropa");
        ArrayList<Categoria> categorias= new ArrayList<Categoria>();
        categoriaRepository.save(categoria1);
        categorias.add(categoria1);

        ArrayList<String> imagenes = new ArrayList<String>();
        Producto producto = new Producto(1, "Camiseta", 22, "Camiseta negra",imagenes, 25000D, ldt, usuario, ciudad, categorias);

        Producto guardado = productoRepository.save(producto);

        ldt =  LocalDateTime.of(2021, 12, 12, 19, 15, 50);
        Subasta subasta = new Subasta(1, ldt, producto);

        Subasta guardada = subastaRepository.save(subasta);

        SubastaUsuario subastaUsuario = new SubastaUsuario(1,1542, LocalDateTime.now(), subasta, usuario);

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

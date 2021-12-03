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
public class ProductoTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /*
    Prueba para saber si los productos se están guardando bien en la base de datos.
     */
    @Test
    public void registrarTest(){
        //Creo un producto y lo guardo.
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
        System.out.println(guardado.toString());
        Assertions.assertNotNull(guardado);
    }

    /*
    Se comprueba que productos se eliminen correctamente.
     */
    @Test
    @Sql("classpath:producto.sql")
    public void eliminarTest(){
        //Se elimina el producto 1 y luego se busca ese mismo producto.
        productoRepository.deleteById(1);
        Producto producto = productoRepository.findById(1).orElse(null);
        Assertions.assertNull(producto);
    }

    /*
    Se comprueba que los productos se puedan actualizar.
     */
    @Test
    @Sql("classpath:producto.sql")
    public void actualizarTest(){
        //Se saca un producto ya creado.
        Producto productoGuardado = productoRepository.findById(1).orElse(null);

        //Se cambia la descripción del producto encontrado y luego se vuelve a guardar(se actualiza)
        productoGuardado.setDescripcion("Playera color rojo");
        productoRepository.save(productoGuardado);

        Producto producto = productoRepository.findById(1).orElse(null);
        Assertions.assertEquals("Playera color rojo", producto.getDescripcion());
    }

    /*
    Se comprueba que se puedan leer los productos guardados.
     */
    @Test
    @Sql("classpath:producto.sql")
    public void ListarTest(){
        //Se enlistan todos los productos guardados y se muestran.
        List<Producto> productos = productoRepository.findAll();
        productos.forEach( p -> System.out.println(p));
    }
}

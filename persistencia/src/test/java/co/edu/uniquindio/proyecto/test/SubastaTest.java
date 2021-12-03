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
public class SubastaTest {

    @Autowired
    private SubastaRepository subastaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    /*
    Prueba para saber si las subastas se están guardando bien en la base de datos.
     */
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
        Assertions.assertNotNull(guardada);
    }

    /*
    Se comprueba que las subastas se eliminen correctamente.
     */
    @Test
    @Sql("classpath:subasta.sql")
    public void eliminarTest(){
        //Se elimina la subasta 1 y luego se busca esa misma subasta.
        subastaRepository.deleteById(1);
        Subasta subasta = subastaRepository.findById(1).orElse(null);
        Assertions.assertNull(subasta);
    }

    /*
    Se comprueba que las subastas se puedan actualizar.
     */
    @Test
    @Sql("classpath:subasta.sql")
    public void actualizarTest(){
        //Se saca una subasta ya creada.
        Subasta subastaGuardada = subastaRepository.findById(1).orElse(null);

        //Se cambia la fecha limite de la subasta encontrada y luego se vuelve a guardar(se actualiza)
        subastaGuardada.setFechaLimite(LocalDateTime.of(2022, 11, 20, 19, 55, 20));
        subastaRepository.save(subastaGuardada);

        Subasta subasta = subastaRepository.findById(1).orElse(null);
        Assertions.assertEquals(LocalDateTime.of(2022, 11, 20, 19, 55, 20), subasta.getFechaLimite());
    }

    /*
    Se comprueba que se puedan leer las subastas guardadas.
     */
    @Test
    @Sql("classpath:subasta.sql")
    public void ListarTest(){
        //Se enlistan todas las subastas guardadas y se muestran.
        List<Subasta> subastas = subastaRepository.findAll();
        subastas.forEach( s -> System.out.println(s));
    }

    @Test
    @Sql("classpath:subasta.sql")
    public void ListarTotalProductosPorCategoría(){
        List<Object[]> respuesta = subastaRepository.obtenerTotalProductosSubastadosPorCategoría();
        respuesta.forEach(r -> System.out.println(r[0] +", "+ r[1]));
    }

    @Test
    @Sql("classpath:subasta.sql")
    public void ListarChatsDeVendedor() {
        List<Subasta> respuesta = subastaRepository.obtenerSubastasVigentesPorCategoría(4);
        respuesta.forEach(s -> System.out.println(s));
    }
}


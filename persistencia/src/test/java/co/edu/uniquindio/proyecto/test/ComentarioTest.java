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
import java.util.HashMap;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    CiudadRepository ciudadRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    /*
    Prueba para saber si los comentarios se están guardando bien en la base de datos.
     */
    @Test
    public void registrarTest(){

        Ciudad ciudad = new Ciudad(1, "Berlín");
        ciudadRepository.save(ciudad);
        //Creo el usuario que realiza el comentario.
        HashMap<String,String> numTelefonos = new HashMap<String, String>();
        numTelefonos.put("Casa","3145324325");
        Usuario usuario = new Usuario(1, "Daniel", "Damomu", "daniel@uqvirtual.edu.co", "1489",ciudad, numTelefonos );
        usuarioRepository.save(usuario);
        //Creo el producto que se va a comentar.
        LocalDateTime ldt = LocalDateTime.of(2022, 11, 4, 23, 11, 03);
        Producto producto = new Producto(5, "Camiseta AC/DC", 10, "Increible camiseta negra con logo de AC/DC", "camiseta", "urlCamisetaACDC", 70000D, ldt, usuario, ciudad);
        productoRepository.save(producto);
        //Creo un comentario y lo guardo.
        Comentario comentario = new Comentario(1,"Me gusta mucho el producto", 4, LocalDateTime.now(), producto, usuario);
        Comentario guardado = comentarioRepository.save(comentario);

        Assertions.assertNotNull(guardado);
    }

    /*
    Se comprueba que los comentarios se eliminen correctamente.
     */
    @Test
    @Sql("classpath:comentario.sql")
    public void eliminarTest(){
        //Se elimina el comentario 1 y luego se busca ese mismo comentario.
        comentarioRepository.deleteById(1);
        Comentario comentario = comentarioRepository.findById(1).orElse(null);
        Assertions.assertNull(comentario);
    }

    /*
    Se compruebe que los comentarios se puedan actualizar.
     */
    @Test
    @Sql("classpath:comentario.sql")
    public void actualizarTest(){
        //Se saca un comentario ya creado.
        Comentario comentarioGuardado = comentarioRepository.findById(1).orElse(null);

        //Se cambia el mensaje del comentario encontrado y luego se vuelve a guardar(se actualiza)
        comentarioGuardado.setCalificacion(4);
        comentarioRepository.save(comentarioGuardado);

        Comentario comentario = comentarioRepository.findById(1).orElse(null);
        Assertions.assertEquals(4, comentario.getCalificacion());
    }

    /*
    Se comprueba que se puedan leer los comentarios guardados.
     */
    @Test
    @Sql("classpath:comentario.sql")
    public void ListarTest(){
        //Se enlistan todos los comentarios guardados y se muestran.
        List<Comentario> comentarios = comentarioRepository.findAll();
        comentarios.forEach(c -> System.out.println(c));
    }

    @Test
    @Sql("classpath:comentario.sql")
    public void ListarCalificacionesPorCategoría(){
        List<Object[]> respuesta = comentarioRepository.listarCalificacionPromedioPorCategoría();
        respuesta.forEach(r -> System.out.println(r[0] +", "+ r[1]));
    }
}

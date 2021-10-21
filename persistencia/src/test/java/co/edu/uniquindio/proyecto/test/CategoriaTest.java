package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepository;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepository;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void registrarTest(){
        //Creo el categoria y la guardo.
        Categoria categoria= new Categoria( 0,"Tecnología");
        Categoria guardada = categoriaRepository.save(categoria);

        Assertions.assertNotNull(guardada);

    }

    @Test
    @Sql("claspath:categorias.sql")
    public void eliminarTest(){

        //categoría tiene código autogenerado, asumimos que tenemos (0,1,2) en ID.
        Categoria categoria = categoriaRepository.findById(2).orElse(null);

//      elimino la categoria.
        categoriaRepository.deleteById(2);

        Categoria categoriaBuscada = categoriaRepository.findById(2).orElse(null);

        Assertions.assertNull(categoriaBuscada);
    }


    @Test
    @Sql("claspath:categorias.sql")
    public void actualizarTest(){

        //Asumimos que tenemos 3 categorías ya predefinidas con código autogenerados(0,1,2) y obtenemos
        Categoria categoria = categoriaRepository.findById(2).orElse(null);

        //Modifico el email.
        categoria.setNombre("Mascotas");

        //Guardo el usuario de nuevo(actualizo).
        categoriaRepository.save(categoria);

        Categoria actualizada = categoriaRepository.findById(2).orElse(null);

        Assertions.assertEquals("Mascotas", actualizada.getNombre());

    }

    @Test
    @Sql("claspath:usuarios.sql")
    public void listarTest(){

        //Obtengo las categorias
        List<Categoria> categorias = categoriaRepository.findAll();

        Assertions.assertEquals( 3, categorias.size());

    }
}

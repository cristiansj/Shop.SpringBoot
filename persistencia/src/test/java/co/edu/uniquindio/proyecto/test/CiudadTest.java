package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    CiudadRepository ciudadRepository;

    /*
    Prueba para saber si las ciudades se están guardando bien en la base de datos.
     */
    @Test
    public void registrarTest(){
        //Creo una ciudad y la guardo.
        Ciudad ciudad = new Ciudad("Medellín");
        Ciudad guardado = ciudadRepository.save(ciudad);

        Assertions.assertNotNull(guardado);
    }

    /*
    Se comprueba que las ciudades se eliminen correctamente.
     */
    @Test
    @Sql("classpath:ciudad.sql")
    public void eliminarTest(){
        //Se elimina la ciudad 1 y luego se busca esa misma ciudad.
        ciudadRepository.deleteById(1);
        Ciudad ciudad = ciudadRepository.findById(1).orElse(null);
        Assertions.assertNull(ciudad);
    }

    /*
    Se compruebe que las ciudades se puedan actualizar.
     */
    @Test
    @Sql("classpath:ciudad.sql")
    public void actualizarTest(){
        //Se saca una ciudad ya creada.
        Ciudad ciudadGuardada = ciudadRepository.findById(1).orElse(null);

        //Se cambia el nombre de la ciudad encontrada y luego se vuelve a guardar(se actualiza)
        ciudadGuardada.setNombre("Cuyabrostán");
        ciudadRepository.save(ciudadGuardada);

        Ciudad ciudad = ciudadRepository.findById(1).orElse(null);
        Assertions.assertEquals("Cuyabrostán", ciudad.getNombre());
    }

    /*
    Se comprueba que se puedan leer las ciudades guardadas.
     */
    @Test
    @Sql("classpath:ciudad.sql")
    public void ListarTest(){
        //Se enlistan todas las ciudades guardadas y se muestran.
        List<Ciudad> ciudades = ciudadRepository.findAll();
        ciudades.forEach(c -> System.out.println(c));
    }
}

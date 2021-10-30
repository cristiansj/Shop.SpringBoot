package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepository;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaTest {

    @Autowired
    SubastaRepository subastaRepository;

    /*
    Prueba para saber si las subastas se están guardando bien en la base de datos.
     */
    @Test
    public void registrarTest(){
        //Creo una subasta y la guardo.
        Subasta subasta = new Subasta(1, LocalDateTime.of(2021, 12, 12, 19, 15, 50));

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

}

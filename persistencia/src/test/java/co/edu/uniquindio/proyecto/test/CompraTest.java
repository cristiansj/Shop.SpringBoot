package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepository;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepository;
import co.edu.uniquindio.proyecto.repositorios.CompraRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepository compraRepository;

    @Test
    public void registrarTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Compra compra = new Compra(1, localDateTime, "Tarjeta");

        Compra compraG = compraRepository.save(compra);
        Assertions.assertNotNull(compraG);
    }

    @Test
    @Sql("classpath:compra.sql")
    public void eliminarTest() {
        compraRepository.deleteById(1);
        Compra compraBuscada = compraRepository.findById(1).orElse(null);
        Assertions.assertNull(compraBuscada);
    }

    @Test
    @Sql("classpath:compra.sql")
    public void actualizarTest() {
        Compra compraG = compraRepository.findById(1).orElse(null);
        compraG.setCodigo(4);
        compraRepository.save(compraG);

        Compra compraBuscado = compraRepository.findById(1).orElse(null);
        Assertions.assertEquals(4, compraBuscado.getCodigo());
    }

    @Test
    @Sql("classpath:compra.sql")
    public void ListarTest() {
        List<Compra> compras = compraRepository.findAll();
        compras.forEach(u -> System.out.println(u));
    }
}

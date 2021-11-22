package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Test
    public void registrarTest() {

        Ciudad ciudad = new Ciudad();
        ciudadRepository.save(ciudad);

        HashMap<String, String> numTelefonos = new HashMap<String, String>();
        numTelefonos.put("Casa","3142534578");

        Usuario usuario = new Usuario(1, "Pepe", "pepito123", "pepito123@gmail.com", "1247", ciudad, numTelefonos);
        usuarioRepository.save(usuario);

        LocalDateTime localDateTime = LocalDateTime.now();

        Compra compra = new Compra(1, localDateTime, "Tarjeta", usuario);

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

    @Test
    @Sql("classpath:compra.sql")
    public void ListarCantidadComprasPorMedioPago(){
        List<Object[]> respuesta = compraRepository.listarTotalComprasPorMedioDePago();
        respuesta.forEach(r -> System.out.println(r[0] +", "+ r[1]));
    }
}

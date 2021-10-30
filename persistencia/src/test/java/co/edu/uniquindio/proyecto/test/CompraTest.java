package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepository;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepository;
import co.edu.uniquindio.proyecto.repositorios.CompraRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepository compraRepository;

    @Test
    public void registrarTest(){
        Compra compra = new Compra(1,"Tarjeta");

        Compra compraG = compraRepository.save(compra);
        Assertions.assertNull(compraG);
    }
}

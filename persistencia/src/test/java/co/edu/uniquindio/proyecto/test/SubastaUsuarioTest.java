package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.repositorios.MensajeRepository;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaUsuarioTest {

    @Autowired
    private SubastaUsuarioRepository subastaUsuarioRepository;
}

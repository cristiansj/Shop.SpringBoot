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
public class DetalleCompraTest {

    @Autowired
    DetalleCompraRepository detalleCompraRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CompraRepository compraRepository;


    /*
    Prueba para saber si los datos detallados de las compras(DetalleCompra) se están guardando bien en la base de datos.
     */
    @Test
    public void registrarTest(){

        Ciudad ciudad = new Ciudad(1, "Armenia");
        ciudadRepository.save(ciudad);

        HashMap<String, String> numTelefonos = new HashMap<>();
        numTelefonos.put("Casa","3175436254");

        Usuario usuario = new Usuario(1, "Pepe", "pepito123", "pepito123@gmail.com", "1245", ciudad, numTelefonos);
        usuarioRepository.save(usuario);

        LocalDateTime ldt = LocalDateTime.now();
        Producto producto = new Producto(1, "Camiseta", 15, "Camiseta blanca", "CamisetaB12", "URLCamisetaB12", 34000D, ldt, usuario, ciudad);
        productoRepository.save(producto);

        LocalDateTime ldt2 = LocalDateTime.now();

        Compra compra = new Compra(1, ldt2, "Tarjeta", usuario);
        compraRepository.save(compra);

        //Creo un detalleCompra y lo guardo.
        DetalleCompra detalleCompra = new DetalleCompra(1, 5, 5000D, compra, producto);

        DetalleCompra guardado = detalleCompraRepository.save(detalleCompra);
        Assertions.assertNotNull(guardado);
    }

    /*
    Se comprueba que los detalles de las compras se eliminen correctamente.
     */
    @Test
    @Sql("classpath:detalleCompra.sql")
    public void eliminarTest(){
        //Se elimina el detalle de compra 1 y luego se busca ese mismo detalle.
        detalleCompraRepository.deleteById(1);
        DetalleCompra detalleCompra = detalleCompraRepository.findById(1).orElse(null);
        Assertions.assertNull(detalleCompra);
    }

    /*
    Se compruebe que los detalles se puedan actualizar.
     */
    @Test
    @Sql("classpath:detalleCompra.sql")
    public void actualizarTest(){
        //Se saca un detalle ya creado.
        DetalleCompra detalleCompraGuardado = detalleCompraRepository.findById(1).orElse(null);

        //Se cambia las unidades del detalle encontrado y luego se vuelve a guardar(se actualiza)
        detalleCompraGuardado.setUnidades(4);
        detalleCompraRepository.save(detalleCompraGuardado);

        DetalleCompra detalleCompra = detalleCompraRepository.findById(1).orElse(null);
        Assertions.assertEquals(4, detalleCompra.getUnidades());
    }

    /*
    Se comprueba que se puedan leer los detalles guardados.
     */
    @Test
    @Sql("classpath:detalleCompra.sql")
    public void ListarTest(){
        //Se enlistan todos los detalles de compras guardados y se muestran.
        List<DetalleCompra> detallesCompras = detalleCompraRepository.findAll();
        detallesCompras.forEach( dc -> System.out.println(dc));
    }
}

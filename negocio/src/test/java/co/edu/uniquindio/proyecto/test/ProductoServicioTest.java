package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicioImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ProductoServicioTest {

    @Autowired
    private ProductoServicioImplementation productoServicioImplementation;

    @Test
    public void publicarProductoTest(){
        Producto producto = new Producto();
        try {
            Producto productoPublicado = productoServicioImplementation.publicarProducto(producto);
            Assertions.assertNotNull(productoPublicado);
        }catch (Exception e){

        }
    }

    @Test
    public void actualizarProductoTest(){
        Usuario usuario = new Usuario(123,"Cristian","cristian20","cristian@gmail.com","123",)
        Producto producto = new Producto(123,"camisa",10,"camisa buena","camisa","hahshhsa",1235.0, LocalDateTime.now(),);
        try {
            Producto productoPublicado = productoServicioImplementation.publicarProducto(producto);
            Assertions.assertNotNull(productoPublicado);
        }catch (Exception e){

        }
    }
}

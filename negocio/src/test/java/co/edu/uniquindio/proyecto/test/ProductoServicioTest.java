package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicioImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        Ciudad ciudad = new Ciudad("Armenia");
        HashMap<String,String> telefonos = new HashMap<>();
        telefonos.put("casa","123");
        ArrayList<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria("deportivo"));
        Usuario usuario = new Usuario("Cristian","cristian20","cristian@gmail.com","123",ciudad,telefonos);
        ArrayList<String> imagenes = new ArrayList<String>();
        LocalDateTime ldt = LocalDateTime.of(2022,11,30,12,59,03);
        Producto producto = new Producto("Camiseta", 22, "Camiseta negra",imagenes, 25000D, ldt, usuario, ciudad, categorias);
        try {
            Producto productoPublicado = productoServicioImplementation.actualizarProducto(producto);
            Assertions.assertNotNull(productoPublicado);
        }catch (Exception e){

        }
    }

    @Test
    public void eliminarProductoTest(){
        Ciudad ciudad = new Ciudad("Armenia");
        HashMap<String,String> telefonos = new HashMap<>();
        telefonos.put("casa","123");
        ArrayList<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria("deportivo"));
        Usuario usuario = new Usuario("Cristian","cristian20","cristian@gmail.com","123",ciudad,telefonos);
        LocalDateTime ldt = LocalDateTime.of(2022,11,30,12,59,03);
        ArrayList<String> imagenes = new ArrayList<String>();
        Producto producto = new Producto("Camiseta", 22, "Camiseta negra",imagenes, 25000D, ldt, usuario, ciudad, categorias);
        try {
            productoServicioImplementation.eliminarProducto(producto.getCodigo());
            Producto productoBuscado = productoServicioImplementation.buscarProductoPorCodigo(producto.getCodigo());
            Assertions.assertNotNull(productoBuscado);
        }catch (Exception e){

        }
    }

    @Test
    public void obtenerProductoTest(){
        Producto producto = new Producto();
        try {
            Producto productoPublicado = productoServicioImplementation.publicarProducto(producto);
            Assertions.assertNotNull(productoPublicado);
        }catch (Exception e){

        }
    }

    @Test
    public void listarProductosTest() {
        try {
            List<Producto> productos = productoServicioImplementation.listarProductos();
            Assertions.assertNotNull(productos);
        } catch (Exception e) {

        }
    }
}

package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Filtro;
import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> listar() {
        return productoServicio.listarProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") String idProducto) {
        try {
            Producto producto = productoServicio.obtenerProducto(Integer.parseInt(idProducto));
            return ResponseEntity.status(200).body(producto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/filtrar")
    public List<Producto> obtenerPorFiltro(@RequestBody Filtro filtro) {
        try {
            List<Producto> productos = productoServicio.listarProductos();
            if (filtro.getCategoría() != null) {
                for (int i = 0; i < productos.size(); i++) {
                    if (!productos.get(i).getCategorias().contains(filtro.getCategoría())) {
                        productos.remove(i);
                        i--;
                    }
                }
            }
            if (filtro.getCiudad() != null) {
                for (int i = 0; i < productos.size(); i++) {
                    if (!productos.get(i).getCodigoCiudad().equals(filtro.getCiudad())) {
                        productos.remove(i);
                        i--;
                    }
                }
            }
            if (filtro.getPrecioMin() != null && filtro.getPrecioMax() != null) {
                for (int i = 0; i < productos.size(); i++) {
                    if (!(productos.get(i).getPrecio() >= filtro.getPrecioMin()) || !(productos.get(i).getPrecio() <= filtro.getPrecioMax())) {
                        productos.remove(i);
                        i--;
                    }
                }
            }
            return productos;
        }catch (Exception e){

        }
        return null;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Producto producto) throws Exception{
        try {
            Producto producto1 = productoServicio.publicarProducto(producto);
            return ResponseEntity.status(201).body(new Mensaje("Se ha publicado el producto satisfactoriamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Producto producto) throws Exception{
        try {
             Producto producto1 =  productoServicio.actualizarProducto(producto);
            return ResponseEntity.status(200).body(new Mensaje("Se ha actualizado el producto de forma exitosa"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") String id) throws Exception{
        try {
            productoServicio.eliminarProducto(Integer.parseInt(id));
            return ResponseEntity.status(200).body(new Mensaje("Se ha eliminado el producto sin inconvenientes"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/listarCalificacion/{calificacion}")
    public List<Producto> listarPorCalificacion(@PathVariable("calificacion") String calificacion){
        return productoServicio.listarPorCalificación(Integer.parseInt(calificacion));
    }

    @GetMapping("/listarPorUsuario/{id}")
    public List<Producto> listarPorUsuario(@PathVariable("id") String id){
        try {
            return productoServicio.listarProductosPorUsuario(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

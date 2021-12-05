package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto producto)throws Exception;

    Producto actualizarProducto(Producto producto)throws Exception;

    Producto eliminarProducto(Integer codigo)throws Exception;

    Producto obtenerProducto(Integer codigo)throws Exception;

    List<Producto> listarProductos();

    List<Producto> listarProductosPorCategoria(Categoria categoria)throws Exception;

    void comentarProducto(Comentario comentario) throws Exception;

    void responderComentario(Comentario comentario, String mensaje) throws Exception;

    void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    Compra comprarProductos(Usuario usuario, List<ProductoCarrito> productos, String medioPago) throws Exception;

    List<Producto> buscarProducto(String nombreProducto, String[] filtros) throws Exception;

    List<Producto> listarProductosPorUsuario(Integer codigoUsuario) throws Exception;

    void agregarProductoAlCarrito(Usuario usuario, Producto producto) throws Exception;

    void removerProductoDelCarrito(Usuario usuario, Producto producto) throws Exception;

    Integer sacarCalificaciónProducto(Producto producto)throws Exception;

    List<Categoria> listarCategoria();

    Categoria obtenerCategoria(String categoria) throws Exception;
}

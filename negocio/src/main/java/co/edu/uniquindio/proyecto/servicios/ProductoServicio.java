package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto producto)throws Exception;

    Producto actualizarProducto(Producto producto)throws Exception;

    Producto eliminarProducto(Integer codigo)throws Exception;

    Producto obtenerProducto(Integer codigo)throws Exception;

    List<Producto> listarProductos();

    List<Producto> listarProductosPorCategoria(Categoria categoria)throws Exception;

    void comentarProducto(String mensaje, Integer calificacion, Usuario usuario, Producto producto) throws Exception;

    void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void comprarProductos(Compra compra, Integer codigoUsuario) throws Exception;

    List<Producto> buscarProducto(String nombreProducto, String[] filtros) throws Exception;

    List<Producto> listarProductosPorUsuario(Integer codigoUsuario) throws Exception;

    void agregarProductoAlCarrito(Usuario usuario, Producto producto) throws Exception;

    void removerProductoDelCarrito(Usuario usuario, Producto producto) throws Exception;

    Integer sacarCalificaciónProducto(Producto producto)throws Exception;

}

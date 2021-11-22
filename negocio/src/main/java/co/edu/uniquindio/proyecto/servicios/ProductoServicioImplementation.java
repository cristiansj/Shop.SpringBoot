package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepository;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepository;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImplementation implements ProductoServicio {

    private final ProductoRepository productoRepository;

    private final UsuarioRepository usuarioRepository;

    private final ComentarioRepository comentarioRepository;

    public ProductoServicioImplementation(ProductoRepository productoRepository, UsuarioRepository usuarioRepository, ComentarioRepository comentarioRepository) {
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public Producto publicarProducto(Producto producto) throws Exception {

        try {
            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public Producto actualizarProducto(Producto producto) throws Exception {

        Optional<Producto> buscado = productoRepository.findById(producto.getCodigo());
        if (buscado.isEmpty()) {
            throw new Exception("El producto que se quiere actualizar no existe");
        }

        Producto actulizado = buscado.get();

        actulizado.setDescripcion(producto.getDescripcion());
        actulizado.setDescuento(producto.getDescuento());
        actulizado.setDisponibilidad(producto.getDisponibilidad());
        actulizado.setFechaLimite(producto.getFechaLimite());
        actulizado.setNombre(producto.getNombre());
        actulizado.setPrecio(producto.getPrecio());

        return productoRepository.save(actulizado);
    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepository.findById(codigo);

        if (producto.isEmpty()) {
            throw new Exception("No hay ningún producto asociado al código ingresado");
        }

        productoRepository.deleteById(codigo);

    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {
        return productoRepository.findById(codigo).orElseThrow(() -> new Exception("EL código ingresado no está asociado a ningún producto"));
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> listarProductosPorCategoria(Categoria categoria) {
        return productoRepository.listarProductosPorCategoria(categoria);
    }

    @Override
    public void comentarProducto(String mensaje, Integer calificacion, Usuario usuario, Producto producto) throws Exception {

        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(usuario.getCodigo());

        if (usuarioBuscado.isEmpty()) {
            throw new Exception("El usuario que quiere comentar el producto no existe");
        }

        Optional<Producto> productoBuscado = productoRepository.findById(producto.getCodigo());

        if (productoBuscado.isEmpty()) {
            throw new Exception("El producto que  se quiere comentar el no existe");
        }

        Comentario cometario = new Comentario(1, "Increible producto", 5, LocalDateTime.now(), producto, usuario);

        comentarioRepository.save(cometario);
    }

    @Override
    public void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(usuario.getCodigo());

        if (usuarioBuscado.isEmpty()) {
            throw new Exception("El usuario buscado no se encuentra registrado");
        }

        Optional<Producto> productoBuscado = productoRepository.findById(producto.getCodigo());
        if (productoBuscado.isEmpty()){
            throw new Exception("El producto ingresado no existe");
        }

        if (usuarioBuscado.get().getProductosFavoritos().contains(producto)) {
            throw new Exception("El producto que se quiere añadir favoritos ya está en la lista de favoritos");
        }

        usuarioBuscado.get().getProductosFavoritos().add(producto);

    }

    @Override
    public void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(usuario.getCodigo());

        if (usuarioBuscado.isEmpty()) {
            throw new Exception("El usuario buscado no se encuentra registrado");
        }

        Optional<Producto> productoBuscado = productoRepository.findById(producto.getCodigo());
        if (productoBuscado.isEmpty()){
            throw new Exception("El producto ingresado no existe");
        }

        if (!usuarioBuscado.get().getProductosFavoritos().contains(producto)) {
            throw new Exception("El producto que se quiere remover de favoritos no está en la lista de favoritos");
        }

        usuarioBuscado.get().getProductosFavoritos().remove(producto);

    }

    @Override
    public void comprarProductos(Compra compra) throws Exception {

    }

    @Override
    public void agregarProductoAlCarrito(Usuario usuario, Producto producto) throws Exception {

        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(usuario.getCodigo());

        if (usuarioBuscado.isEmpty()) {
            throw new Exception("El usuario buscado no se encuentra registrado");
        }

        Optional<Producto> productoBuscado = productoRepository.findById(producto.getCodigo());
        if (productoBuscado.isEmpty()){
            throw new Exception("El producto ingresado no existe");
        }

        if (usuarioBuscado.get().getCarrito().contains(producto)) {
            throw new Exception("El producto que se quiere agregar al carrito ya está en el carrito");
        }

        usuarioBuscado.get().getCarrito().add(producto);

    }

    @Override
    public void removerProductoDelCarrito(Usuario usuario, Producto producto) throws Exception {

        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(usuario.getCodigo());

        if (usuarioBuscado.isEmpty()) {
            throw new Exception("El usuario buscado no se encuentra registrado");
        }

        Optional<Producto> productoBuscado = productoRepository.findById(producto.getCodigo());
        if (productoBuscado.isEmpty()){
            throw new Exception("El producto ingresado no existe");
        }

        if (!usuarioBuscado.get().getCarrito().contains(producto)) {
            throw new Exception("El producto que se quiere sacar del carrito no está en el carrito");
        }

        usuarioBuscado.get().getCarrito().remove(producto);

    }

    @Override
    public Integer sacarCalificaciónProducto(Producto producto) throws Exception {

        Optional<Producto> productoBuscado = productoRepository.findById(producto.getCodigo());

        if (productoBuscado.isEmpty()) {
            throw new Exception("El producto buscado no se encuentra registrado");
        }

        return productoRepository.sacarCalificaciónPromedio(producto);

    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto, String[] filtros) {

        return null;

    }

    @Override
    public List<Producto> listarProductosPorUsuario(Integer codigoUsuario) throws Exception {

        return productoRepository.listarProductosDeUsuario(codigoUsuario);

    }

}




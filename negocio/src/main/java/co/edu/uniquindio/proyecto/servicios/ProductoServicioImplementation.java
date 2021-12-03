package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepository;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepository;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepository;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImplementation implements ProductoServicio {

    private final ProductoRepository productoRepository;

    private final CategoriaRepository categoriaRepository;

    private final UsuarioRepository usuarioRepository;

    private final ComentarioRepository comentarioRepository;

    public Producto buscarProductoPorCodigo(Integer codigoProducto) {
        return productoRepository.findById(codigoProducto).get();
    }

    public ProductoServicioImplementation(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository, ComentarioRepository comentarioRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
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
    public Producto eliminarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepository.findById(codigo);

        if (producto.isEmpty()) {
            throw new Exception("No hay ningún producto asociado al código ingresado");
        }

        productoRepository.deleteById(codigo);
        return producto.get();
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {
        return productoRepository.findById(codigo).orElseThrow(() -> new Exception("EL código ingresado no está asociado a ningún producto"));
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    //voy aquí
    @Override
    public List<Producto> listarProductosPorCategoria(Categoria categoria) {
        return productoRepository.listarProductosPorCategoria(categoria);
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFechaComentario(LocalDateTime.now());
        if (comentario != null) {
            comentarioRepository.save(comentario);
        }
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
        int tamanio = compra.getDetallesCompras().size();
        for (int i = 0; i < tamanio; i++) {
            Optional<Usuario> usuario = usuarioRepository.findById(compra.getDetallesCompras().get(i).getCodigoProducto().getCodigoVendedor().getCodigo());
            usuario.get().getProductos().remove(compra.getDetallesCompras().get(i).getCodigoProducto());
        }
    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto, String[] filtros) throws Exception {
        List<Producto> productos = productoRepository.findByNombreContains(nombreProducto);
        if (productos.isEmpty()) {
            throw new Exception("No hemos encontrado coincidencias");
        }
        if (filtros != null) {
            if (filtros[0] != "") {
                for (int i = 0; i < productos.size(); i++) {
                    int tamanio = productos.get(i).getCategorias().size();
                    boolean bandera = true;
                    for (int j = 0; j < tamanio && bandera; j++) {
                        if (productos.get(i).getCategorias().get(j).equals(filtros[0])) {
                            bandera = false;
                        }
                    }
                    if (bandera) {
                        productos.remove(i);
                    }
                }
            }
            if (filtros[1] != "") {
                for (int i = 0; i < productos.size(); i++) {
                    if (!(productos.get(i).getPrecio() == Double.parseDouble(filtros[1]))) {
                        productos.remove(i);
                    }
                }
            }
            if (filtros[2] != "") {
                for (int i = 0; i < productos.size(); i++) {
                    if (!(productos.get(i).getCodigoCiudad().getNombre().equals(filtros[2]))) {
                        productos.remove(i);
                    }
                }
            }
            if (filtros[3] != "") {
                for (int i = 0; i < productos.size(); i++) {
                    Integer calificacionPromedio = productoRepository.sacarCalificaciónPromedio(productos.get(i));
                    if (!(calificacionPromedio == Integer.parseInt(filtros[3]))) {
                        productos.remove(i);
                    }
                }
            }
        }
        return productos;
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
    public List<Categoria> listarCategoria() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria obtenerCategoria(String categoria) throws Exception {
        return categoriaRepository.findByNombreContains(categoria);
    }

    @Override
    public List<Producto> listarProductosPorUsuario(Integer codigoUsuario) throws Exception {
        return productoRepository.listarProductosDeUsuario(codigoUsuario);
    }

}




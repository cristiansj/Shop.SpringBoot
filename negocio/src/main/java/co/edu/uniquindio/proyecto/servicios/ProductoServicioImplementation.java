package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImplementation implements ProductoServicio {

    private final ProductoRepository productoRepository;

    private final CategoriaRepository categoriaRepository;

    private final UsuarioRepository usuarioRepository;

    private final ComentarioRepository comentarioRepository;

    private final CompraRepository compraRepository;

    private final DetalleCompraRepository detalleCompraRepository;

    private final SubastaRepository subastaRepository;

    public Producto buscarProductoPorCodigo(Integer codigoProducto) {
        return productoRepository.findById(codigoProducto).get();
    }

    public ProductoServicioImplementation(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository, ComentarioRepository comentarioRepository, CompraRepository compraRepository, DetalleCompraRepository detalleCompraRepository, SubastaRepository subastaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.comentarioRepository = comentarioRepository;
        this.compraRepository = compraRepository;
        this.detalleCompraRepository = detalleCompraRepository;
        this.subastaRepository = subastaRepository;
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
        System.out.println(categoria.getNombre());
        List<Producto> productos = productoRepository.listarProductosPorCategoria(categoria);
        System.out.println(productos);
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
    public void responderComentario(Comentario comentario, String mensaje) throws Exception {
        if (comentario != null) {
            comentario.setRespuesta(mensaje);
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
        usuarioRepository.save(usuarioBuscado.get());
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
        usuarioRepository.save(usuarioBuscado.get());
    }

    @Override
    public Compra comprarProductos(Usuario usuario, List<ProductoCarrito> productos, String medioPago) throws Exception {
        Compra compra = new Compra();

        compra.setCodigoUsuario(usuario);
        compra.setFechaCompra(LocalDateTime.now(ZoneId.of("America/Bogota")));
        compra.setMedioPago(medioPago);

        Compra compraGuardada = compraRepository.save(compra);

        DetalleCompra dc;
        for (ProductoCarrito p : productos) {
            dc = new DetalleCompra();
            dc.setPrecioProducto(p.getPrecio());
            dc.setUnidades(p.getUnidades());
            dc.setCodigoCompra(compraGuardada);
            dc.setCodigoProducto(productoRepository.findById(p.getId()).get());
            detalleCompraRepository.save(dc);
            Producto producto = productoRepository.findById(p.getId()).get();
            producto.setDisponibilidad(producto.getDisponibilidad()-p.getUnidades());
            productoRepository.save(producto);
        }

        return compraGuardada;
    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto, Categoria categoria) throws Exception {
        List<Producto> productos = productoRepository.findByNombreContains(nombreProducto);
        if (productos.isEmpty()) {
            throw new Exception("No hemos encontrado coincidencias");
        }
        if (categoria != null) {
            for (int i = 0; i < productos.size(); i++) {
                if (!(productos.get(i).getCategorias().contains(categoria))) {
                    productos.remove(i);
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
    public List<Producto> listarPorCalificación(Integer calificacion) {
        List<Producto> productosCalificacion = productoRepository.findAll();
        for (int i = 0; i<productosCalificacion.size(); i++){
            if (productoRepository.sacarCalificaciónPromedio(productosCalificacion.get(i)) != calificacion){
                productosCalificacion.remove(i);
                i--;
            }
        }
        return productosCalificacion;
    }

    @Override
    public Integer contarFavorito(Integer codigoProducto) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Producto producto = productoRepository.findById(codigoProducto).get();
        int contador = 0;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getProductosFavoritos().contains(producto)) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public List<Categoria> listarCategoria() {
        return categoriaRepository.findAll();
    }

    @Override
    public void subastarProducto(LocalDateTime ldt, Producto producto) throws Exception {
        List<Subasta> subastas = subastaRepository.findAll();
        boolean bandera = true;
        if (ldt.isBefore(LocalDateTime.now()) || ldt.isAfter(LocalDateTime.now().plusHours(24))){
            throw new Exception("La subasta no puede durar más de un día ni cerrarse antes de la fecha actual bobo");
        }
        for (int i=0; i < subastas.size() && bandera;i++){
            if(subastas.get(i).getProducto().equals(producto)){
                throw new Exception("El producto ya se está subastando, debe esperar que la subasta termine para volver a subastar el producto");
            }
        }
        Subasta subasta = new Subasta(ldt,producto);
        subastaRepository.save(subasta);
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




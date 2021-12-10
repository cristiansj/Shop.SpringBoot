package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.dto.ProductoComprado;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.AdministradorServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.SendMailService;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private boolean admin;

    @Getter @Setter
    private Administrador administradorSesion;

    @Getter @Setter
    private String email, password;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    AdministradorServicio administradorServicio;

    @Getter @Setter
    private List<ProductoCarrito> productosCarrito;

    private List<Producto> misProductos;

    private List<Producto> misProductosFavoritos;

    @Getter @Setter
    private Double subTotal;


    private List<ProductoComprado> misCompras;

    @Autowired
    SendMailService sendMailService;

    @PostConstruct
    public void inicializar(){
        this.subTotal = 0D;
        this.productosCarrito = new ArrayList<>();
        this.misProductos = new ArrayList<>();
        this.misProductosFavoritos = new ArrayList<>();
        this.misCompras = new ArrayList<>();
    }

    public String iniciarSesion(){
        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                usuarioSesion = usuarioServicio.hacerLogin(email, password);
                autenticado = true;
                return "/index.xhtml?faces-redirect=true";
            } catch (Exception e) {
                try{
                    administradorSesion = administradorServicio.hacerLogin(email, password);
                    this.admin = true;
                    return "/index.xhtml?faces-redirect=true";
                }catch (Exception e1){
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                    FacesContext.getCurrentInstance().addMessage("login-bean",fm);
                }
            }
        }
        return "";
    }

    public String cerrarSesion(){
        this.admin = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public void agregarAlCarrito(Integer id, Double precio, String nombre, String imagen){
        ProductoCarrito productoCarrito = new ProductoCarrito(id,nombre,imagen,precio,1);
        if (!productosCarrito.contains(productoCarrito)) {
            productosCarrito.add(productoCarrito);
            subTotal += precio;
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta", "Producto añadido al carrito");
            FacesContext.getCurrentInstance().addMessage("msj-bean", fm);
        }else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", "El producto ya está en el carrito");
            FacesContext.getCurrentInstance().addMessage("msj-bean", fm);
        }

    }

    public void eliminarDelCarrito(int indice){
        subTotal -= productosCarrito.get(indice).getPrecio();
        productosCarrito.remove(indice);
        FacesMessage fm1 = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta", "se ha borrado exitosamente el producto");
        FacesContext.getCurrentInstance().addMessage("borrar-bean", fm1);
    }

    public void actualizarSubTotal(){
        subTotal = 0D;
        for (ProductoCarrito p : productosCarrito) {
            subTotal += p.getPrecio()*p.getUnidades();
        }
    }

    @PostMapping("/sendMail")
    public String comprar(){
        boolean bandera = true;
        if (productosCarrito != null && !productosCarrito.isEmpty()) {
            for (int i = 0; i < productosCarrito.size() && bandera; i++){
                Producto producto = null;
                try {
                    producto = productoServicio.obtenerProducto(productosCarrito.get(i).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!(producto.getDisponibilidad() >= productosCarrito.get(i).getUnidades())){
                    bandera = false;
                    FacesMessage fm1 = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", "No hay suficientes unidades de "+productosCarrito.get(i).getNombre()+". Este producto cuenta unicamente con "+producto.getDisponibilidad()+" unidades");
                    FacesContext.getCurrentInstance().addMessage("comprar-bean", fm1);
                }
            }
            if (bandera) {
                try {
                    Compra compra = productoServicio.comprarProductos(usuarioSesion, productosCarrito, "PSE");
                    productosCarrito.clear();
                    subTotal = 0D;
                    sendMailService.sendEmail("unishopdamomu@gmail.com", usuarioSesion.getEmail(),
                                "Información de compra",
                                "Usted ha realizado una compra desde Unishop usando este correo, muchas gracias por preferirnos");
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta", "compra exitosa, gracias por preferirnos :)");
                    FacesContext.getCurrentInstance().addMessage("comprar-bean", fm);
                } catch (Exception e) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                    FacesContext.getCurrentInstance().addMessage("comprar-bean", fm);
                }
            }
        }
        return "../index.xhtml";
    }

    public String getTelefonos(){
        return usuarioSesion.getNumTelefonos().toString();
    }

    public List<Producto> getMisProductos(){
        if (autenticado) {
            this.misProductos = usuarioServicio.listarMisProductos(usuarioSesion.getCodigo());
        }
        return misProductos;
    }

    public List<Producto> getMisProductosFavoritos(){
        if (autenticado) {
            try {
                this.misProductosFavoritos = usuarioServicio.listarProductosFavoritos(usuarioSesion.getEmail());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return misProductosFavoritos;
    }

    public void actualizarDescuento(Producto producto){
        try {
            productoServicio.actualizarProducto(producto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(Integer codigo){
        try {
            productoServicio.eliminarProducto(codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarProductoFavorito(Producto producto){
        try {
            productoServicio.eliminarProductoFavorito(producto,usuarioSesion);
            this.misProductosFavoritos.clear();
            this.misProductosFavoritos = usuarioServicio.listarProductosFavoritos(usuarioSesion.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductoComprado> getMisCompras(){
        misCompras.clear();
        List<Compra> usuarioCompra = usuarioServicio.listarCompras(usuarioSesion.getCodigo());
        List<DetalleCompra> comprasUsuario = new ArrayList<>();
        for (int i = 0; i < usuarioCompra.size(); i++) {
            comprasUsuario = usuarioCompra.get(i).getDetallesCompras();
            for (int j = 0; j < comprasUsuario.size(); j++) {
                Producto producto = comprasUsuario.get(j).getCodigoProducto();
                ProductoComprado productoComprado = new ProductoComprado(producto.getCodigo(),producto.getNombre(), producto.getImagenPrincipal(), producto.getPrecio(), comprasUsuario.get(j).getUnidades(),usuarioCompra.get(i).getFechaCompra());
                misCompras.add(productoComprado);            }
        }
        return this.misCompras;
    }

    public void send(){
    }

    public String ir(){
        return "registrar_usuario.xhtml?faces-redirect=true";
    }

    @GetMapping("/")
    public String enviar(){
        return "index.xhtml";
    }

}

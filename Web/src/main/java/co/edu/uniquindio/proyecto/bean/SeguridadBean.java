package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
    private String email, password;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Getter @Setter
    private List<ProductoCarrito> productosCarrito;

    @Getter @Setter
    private Double subTotal;

    @PostConstruct
    public void inicializar(){
        this.subTotal = 0D;
        this.productosCarrito = new ArrayList<>();
    }

    public String iniciarSesion(){
        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                usuarioSesion = usuarioServicio.hacerLogin(email, password);
                autenticado = true;
                return "/index.xhtml?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean",fm);
            }
        }
        return "";
    }

    public String cerrarSesion(){
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

    }

    public void actualizarSubTotal(){
        subTotal = 0D;
        for (ProductoCarrito p : productosCarrito) {
            subTotal += p.getPrecio()*p.getUnidades();
        }
    }

    public void comprar(){
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
                    productoServicio.comprarProductos(usuarioSesion, productosCarrito, "PSE");
                    productosCarrito.clear();
                    subTotal = 0D;
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta", "compra exitosa, gracias por preferirnos :)");
                    FacesContext.getCurrentInstance().addMessage("comprar-bean", fm);
                } catch (Exception e) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                    FacesContext.getCurrentInstance().addMessage("comprar-bean", fm);
                }
            }
        }
    }
}

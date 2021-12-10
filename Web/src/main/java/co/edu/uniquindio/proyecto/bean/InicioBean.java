package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Getter @Setter
    private List<Producto> productos;

    @PostConstruct
    public void inicializar(){
        this.productos = productoServicio.listarProductos();
        for (int i = 0; i < productos.size();i++) {
            System.out.println(i+":"+productos.get(i).getDisponibilidad());
            if (productos.get(i).getDisponibilidad() == 0) {
                productos.remove(i);
                i--;
            }
        }
    }

    public String irADetalle(String id){
        return "/detalle_producto.xhtml?faces-redirect=true&amp;producto="+id;
    }

    public String irADetallePorUsuario(String id){
        return "../detalle_producto.xhtml?faces-redirect=true&amp;producto="+id;
    }
}

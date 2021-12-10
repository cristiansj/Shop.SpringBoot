package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.SubastaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class SubastaUsuarioBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private SubastaServicio subastaServicio;

    @Value("#{param['producto']}")
    @Getter @Setter
    private String codigoProducto;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private boolean enSubasta;

    @PostConstruct
    public void inicializar(){
        try {
            producto = productoServicio.obtenerProducto(Integer.parseInt(codigoProducto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void subastarProducto(){

    }
}

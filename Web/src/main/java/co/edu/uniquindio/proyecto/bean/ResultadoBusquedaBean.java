package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class ResultadoBusquedaBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Value("#{param['categoria']}")
    private String categoria;

    @Getter @Setter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    private List<Producto> productos;

    @PostConstruct
    public void inicializar(){
        System.out.println(categoria);
        if (busquedaParam != null && !busquedaParam.isEmpty()){
            try {
                productos = productoServicio.buscarProducto(busquedaParam,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Producto> getProductosFiltrados(){
        if (categoria != null && !categoria.isEmpty() && categoria != "0"){
            try {
                Categoria aux = categoriaServicio.obtenerPorID(Integer.parseInt(categoria));
                System.out.println(aux);
                productos = productoServicio.buscarProducto(busquedaParam,aux);
                return productos;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productos;
    }
}

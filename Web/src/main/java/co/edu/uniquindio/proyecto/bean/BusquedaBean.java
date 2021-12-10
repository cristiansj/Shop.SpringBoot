package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepository;
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
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    @Getter @Setter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    private List<Categoria> categorias;

    @Getter @Setter
    private  Categoria categoria;

    @Getter @Setter
    private List<Categoria> filtros;

    @Getter @Setter
    private List<Producto> productos;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @PostConstruct
    public void inicializar(){
        categorias = categoriaServicio.listarCategoria();
        filtros = new ArrayList<>();
    }

    public String buscar() {
        if (categoria != null) {
            return "resultado_busqueda?faces-redirect=true&amp;busqueda="+busqueda+"&amp;categoria="+categoria.getCodigo();
        }else{
            return "resultado_busqueda?faces-redirect=true&amp;busqueda="+busqueda+"&amp;categoria=0";
        }
    }
}

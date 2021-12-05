package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
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
public class DetalleProductoBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{param['producto']}")
    @Getter @Setter
    private String codigoProducto;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private boolean favorito;

    @Getter @Setter
    private Integer calificacionPromedio;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter @Setter
    private List<Comentario> comentarios;

    @PostConstruct
    public void inicializar(){
        nuevoComentario = new Comentario();
        if(codigoProducto != null && !codigoProducto.isEmpty()){
            try {
                producto = productoServicio.obtenerProducto(Integer.parseInt(codigoProducto));
                calificacionPromedio = productoServicio.sacarCalificaciónProducto(producto);
            } catch (Exception e) {
                e.printStackTrace();
            }
            comentarios = producto.getComentarios();
        }
    }

    public void crearComentario(){
        try {
            if (usuarioSesion != null) {
                nuevoComentario.setCodigoProducto(producto);
                nuevoComentario.setCodigoUsuario(usuarioSesion);
                productoServicio.comentarProducto(nuevoComentario);
                this.comentarios.add(nuevoComentario);
                nuevoComentario = new Comentario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void aniadirFavorito(){
        if (usuarioSesion != null) {
            try {
                Producto producto = productoServicio.obtenerProducto(Integer.parseInt(codigoProducto));
                productoServicio.guardarProductoFavorito(producto,usuarioSesion);
                favorito = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

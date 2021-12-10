package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.dto.ProductoComprado;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class ComprasUsuarioBean implements Serializable {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

}

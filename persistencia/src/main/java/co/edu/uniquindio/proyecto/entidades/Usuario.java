package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario extends Persona implements Serializable {

    @ManyToOne
    private Ciudad ciudad;

    @Column(nullable = false, unique = true)
    private String username;

    @ManyToMany
    @ToString.Exclude
    private List<Producto> productosFavoritos;

    @ManyToMany
    @ToString.Exclude
    private List<Producto> carrito;

    @OneToMany(mappedBy = "usuarioComprador")
    @ToString.Exclude
    private List<Chat> chat;

    @OneToMany(mappedBy = "codigoVendedor")
    @ToString.Exclude
    private List<Producto> productos;

    @OneToMany(mappedBy = "codigoUsuario")
    @ToString.Exclude
    private List<Comentario>comentarios;

    @OneToMany(mappedBy = "codigoUsuario")
    @ToString.Exclude
    private List<SubastaUsuario> subastaUsuarios;

    @OneToMany(mappedBy = "codigoUsuario")
    @ToString.Exclude
    private List<Compra> compras;

    public Usuario(Integer codigo, String nombre, String username, String email, String password, Ciudad ciudad, HashMap<String, String> numTelefonos) {
        super(codigo, nombre, email, password, numTelefonos);
        this.username = username;
        this.ciudad = ciudad;
    }
}

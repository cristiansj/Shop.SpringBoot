package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Producto> productosFavoritos;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> carrito;

    @OneToMany(mappedBy = "usuarioComprador")
    @ToString.Exclude
    @JsonIgnore
    private List<Chat> chat;

    @OneToMany(mappedBy = "codigoVendedor")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productos;

    @OneToMany(mappedBy = "codigoUsuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario>comentarios;

    @OneToMany(mappedBy = "codigoUsuario")
    @ToString.Exclude
    @JsonIgnore
    private List<SubastaUsuario> subastaUsuarios;

    @OneToMany(mappedBy = "codigoUsuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Compra> compras;

    public Usuario(String nombre, String username, String email, String password, Ciudad ciudad, HashMap<String, String> numTelefonos) {
        super(nombre, email, password, numTelefonos);
        this.username = username;
        this.ciudad = ciudad;
        this.compras = new ArrayList<>();
    }
}

package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario extends Persona implements Serializable {

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuarioComprador")
    private List<Chat> chat;

    @OneToMany(mappedBy = "codigoVendedor")
    private List<Producto> productos;

    @OneToMany(mappedBy = "codigoUsuario")
    private List<Comentario>comentarios;

    @OneToMany(mappedBy = "codigoUsuario")
    private List<SubastaUsuario> subastaUsuarios;

    @OneToMany(mappedBy = "codigoUsuario")
    private List<Compra> compras;

    public Usuario(Integer codigo, String nombre, String email, String password, Ciudad ciudad) {
        super(codigo, nombre, email, password);
        this.ciudad = ciudad;
    }
}

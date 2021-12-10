package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    private Usuario usuarioComprador;

    @ManyToOne
    private Producto codigoProducto;

    @OneToMany(mappedBy = "codigoChat")
    @ToString.Exclude
    @JsonIgnore
    private List<Mensaje> mensajes;

    public Chat(Usuario usuario, Producto producto) {
        this.codigoProducto = producto;
        this.usuarioComprador = usuario;
    }
}

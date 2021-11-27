package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
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
    private List<Mensaje> mensajes;

    public Chat(Integer codigo, Usuario usuario, Producto producto) {

        this.codigo = codigo;
        this.codigoProducto = producto;
        this.usuarioComprador = usuario;

    }
}

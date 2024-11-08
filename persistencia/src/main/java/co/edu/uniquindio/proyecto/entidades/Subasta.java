package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Subasta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Future
    @Column(nullable = false)
    private LocalDateTime fechaLimite;

    @ManyToOne
    private Producto producto;

    @OneToMany(mappedBy = "codigoSubasta")
    @ToString.Exclude
    @JsonIgnore
    private List<SubastaUsuario> subastaUsuarios;

    public Subasta(LocalDateTime fechaLimite, Producto producto) {
        this.fechaLimite = fechaLimite;
        this.producto = producto;
    }
}

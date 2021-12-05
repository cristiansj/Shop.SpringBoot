package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class SubastaUsuario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    private Integer valor;

    @Column(nullable = false)
    private LocalDateTime fechaSubasta;

    @ManyToOne
    private Subasta codigoSubasta;

    @ManyToOne
    private Usuario codigoUsuario;

    public SubastaUsuario(Integer valor, LocalDateTime fechaSubasta, Subasta subasta, Usuario usuario) {
        this.valor = valor;
        this.fechaSubasta = fechaSubasta;
        this.codigoSubasta = subasta;
        this.codigoUsuario = usuario;
    }
}

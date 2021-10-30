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
public class Comentario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false,length = 250)
    private String mensaje;

    @Column(length = 250)
    private String respuesta;

    @Column(nullable = false, columnDefinition ="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaComentario;

    @Positive
    private Integer calificacion;

    @ManyToOne
    private Producto codigoProducto;

    @ManyToOne
    private Usuario codigoUsuario;

    public Comentario(String mensaje) {
        this.mensaje = mensaje;
    }
}

package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
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


    @PositiveOrZero
    private Integer calificacion;

    @ManyToOne
    private Producto codigoProducto;

    @ManyToOne
    private Usuario codigoUsuario;

    public Comentario(Integer codigo, String mensaje, Integer calificacion, LocalDateTime fechaComentario, Producto producto, Usuario usuario) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.calificacion = calificacion;
        this.fechaComentario = fechaComentario;
        this.codigoProducto = producto;
        this.codigoUsuario = usuario;
    }

    public String getFechaEstilo(){
        String aux = fechaComentario.format(DateTimeFormatter.ISO_DATE_TIME);
        return aux.replaceAll("T"," a las ");
    }
}

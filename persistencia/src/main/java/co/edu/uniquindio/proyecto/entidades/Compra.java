package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Compra implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false, columnDefinition ="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCompra;

    @Column(nullable = false,length = 50)
    private String medioPago;

    @ManyToOne
    private Usuario codigoUsuario;

    @OneToMany(mappedBy = "codigoCompra")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    public Compra(Integer codigo, String medioPago) {
        this.codigo = codigo;
        this.medioPago = medioPago;
    }
}

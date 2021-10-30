package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class DetalleCompra implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    private Integer unidades;

    @Positive
    @Column(nullable = false)
    private Double precioProducto;

    @ManyToOne
    private Compra codigoCompra;

    @ManyToOne
    private Producto codigoProducto;

    public DetalleCompra(Integer codigo, Integer unidades, Double precioProducto) {
        this.codigo = codigo;
        this.unidades = unidades;
        this.precioProducto = precioProducto;
    }
}

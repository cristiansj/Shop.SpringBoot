package co.edu.uniquindio.proyecto.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProductoComprado {
    @EqualsAndHashCode.Include
    private Integer id;
    private String nombre, imagen;
    private Double precio;
    private Integer unidades;
    private LocalDateTime fechaCompra;
}
